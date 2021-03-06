package com.divide2.team.service.impl;

import com.divide2.team.service.AclService;
import com.divide2.team.service.MenuService;
import com.divide2.team.service.WorkbenchInnerConnectService;
import com.divide2.team.vo.MenuTreeVO;
import com.divide2.team.vo.MenuVO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author bvvy
 * @date 2019/2/26
 */
@Service
public class WorkbenchInnerConnectServiceImpl implements WorkbenchInnerConnectService {
    private final AclService aclService;
    private final MenuService menuService;

    public WorkbenchInnerConnectServiceImpl(AclService aclService, MenuService menuService) {
        this.aclService = aclService;
        this.menuService = menuService;
    }

    @Override
    public Set<String> listTeamUserMenuIds(String teamId, Integer userId) {
        Set<String> authorities = aclService.listTeamUserAuthorities(teamId, userId);
        return menuService.findMenuIdsByAuthorities(authorities);
    }

    @Override
    public Map<String,MenuVO> findMapByMenuIds(Collection<String> menuIds) {
        return menuService.findMapByIds(menuIds);
    }

    @Override
    public List<MenuTreeVO> findShortcutMenus() {
        return menuService.findShortcutTree();
    }

}
