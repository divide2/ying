package com.ying.team.service.impl;

import com.ying.core.root.converter.Converter;
import com.ying.team.model.MenuGroup;
import com.ying.team.model.Workbench;
import com.ying.team.repo.MenuGroupRepository;
import com.ying.team.repo.WorkbenchRepository;
import com.ying.team.service.WorkbenchInnerConnectService;
import com.ying.team.service.WorkbenchService;
import com.ying.team.vo.MenuGroupVO;
import com.ying.team.vo.MenuVO;
import com.ying.team.vo.WorkbenchVO;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 工作台
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
        }
        return vos;

    }
}
