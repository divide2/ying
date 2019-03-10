package com.ying.team.service.impl;

import com.ying.core.root.converter.Converter;
import com.ying.team.dto.GroupMenuDTO;
import com.ying.team.model.MenuGroup;
import com.ying.team.model.Workbench;
import com.ying.team.repo.MenuGroupRepository;
import com.ying.team.repo.WorkbenchRepository;
import com.ying.team.service.WorkbenchInnerConnectService;
import com.ying.team.service.WorkbenchService;
import com.ying.team.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 工作台A
 *
 * @author bvvy
 * @date 2019/2/26
 */
@Service
public class WorkbenchServiceImpl implements WorkbenchService {
    private final WorkbenchRepository workbenchRepository;
    private final MenuGroupRepository menuGroupRepository;
    private final WorkbenchInnerConnectService workbenchInnerConnectService;

    public WorkbenchServiceImpl(WorkbenchRepository workbenchRepository,
                                MenuGroupRepository menuGroupRepository,
                                WorkbenchInnerConnectService workbenchInnerConnectService) {
        this.workbenchRepository = workbenchRepository;
        this.menuGroupRepository = menuGroupRepository;
        this.workbenchInnerConnectService = workbenchInnerConnectService;
    }

    @Override
    public void addGroupMenu(GroupMenuDTO dto) {
        Workbench workbench = new Workbench();
        workbench.setTeamId(dto.getTeamId());
        workbench.setMenuGroupId(dto.getMenuGroupId());
        workbench.setMenuId(dto.getMenuId());
        // todo add menu num
        workbench.setOrderNum(1);
        workbenchRepository.save(workbench);
    }

    @Override
    @Transactional
    public void deleteGroupMenu(GroupMenuDTO dto) {
        workbenchRepository.deleteByTeamIdAndMenuGroupIdAndMenuId(dto.getTeamId(), dto.getMenuGroupId(), dto.getMenuId());
    }


    @Override
    public List<WorkbenchMenuVO> listWorkbenchMenu(String menuGroupId) {

        List<Workbench> workbenches = workbenchRepository.findByMenuGroupId(menuGroupId);
        List<String> existMenuIds = Converter.of(workbenches).convert(Workbench::getMenuId);
        List<MenuTreeVO> shortcutTree = workbenchInnerConnectService.findShortcutMenus();
        return Converter.of(shortcutTree).convert(item -> {
            WorkbenchMenuVO wm = new WorkbenchMenuVO(
                    item.getId(),
                    item.getIcon(),
                    item.getName());
            if (existMenuIds.contains(item.getId())) {
                wm.setExist(true);
            }
            List<WorkbenchMenuVO> children = Converter.of(item.getChildren()).convert(jtem -> {
                WorkbenchMenuVO vo = new WorkbenchMenuVO(
                        jtem.getId(),
                        jtem.getIcon(),
                        jtem.getName()
                );
                if (existMenuIds.contains(jtem.getId())) {
                    vo.setExist(true);
                }
                return vo;
            });
            wm.setChildren(children);
            return wm;
        });
    }

    @Override
    public List<WorkbenchVO> getTeamUserWorkbench(String teamId, Integer userId) {
        // 1 查找用户能访问的菜单
        Set<String> menuIds = workbenchInnerConnectService.listTeamUserMenuIds(teamId, userId);
        // 2 查出只有能访问菜单的工作台信息
        List<Workbench> workbenches = workbenchRepository.findByTeamIdAndMenuIds(teamId, menuIds);
        // 3 把菜单分组
        return groupingMenu(teamId, workbenches, menuIds);
    }

    @Override
    public List<WorkbenchVO> getTeamWorkbench(String teamId) {
        List<Workbench> workbenches = workbenchRepository.findByTeamId(teamId);
        List<String> menuIds = Converter.of(workbenches).convert(Workbench::getMenuId);
        // 分组菜单
        return groupingMenu(teamId, workbenches, menuIds);
    }

    private List<WorkbenchVO> groupingMenu(String teamId, List<Workbench> workbenches, Collection<String> menuIds) {
        //先查全部的分组
        List<MenuGroup> teamMenuGroup = menuGroupRepository.findByTeamId(teamId);
        List<WorkbenchVO> vos = new ArrayList<>();
        Map<String, MenuVO> menuMap = workbenchInnerConnectService.findMapByMenuIds(menuIds);
        //这一堆是把可以访问的菜单添加到workbench vo 中
        for (MenuGroup mg : teamMenuGroup) {
            WorkbenchVO vo = new WorkbenchVO();
            List<MenuVO> menus = new ArrayList<>();
            MenuGroupVO menuGroup = new MenuGroupVO(mg.getId(), mg.getName());
            for (Workbench workbench : workbenches) {
                if (menuGroup.getId().equals(workbench.getMenuGroupId()) && menuIds.contains(workbench.getMenuId())) {
                    menus.add(menuMap.get(workbench.getMenuId()));
                }
            }
            vo.setMenuGroup(menuGroup);
            vo.setMenus(menus);
            vos.add(vo);
        }
        return vos;

    }

}
