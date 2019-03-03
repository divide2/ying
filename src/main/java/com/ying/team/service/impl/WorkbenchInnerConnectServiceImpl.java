package com.ying.team.service.impl;

import com.ying.team.service.AclService;
import com.ying.team.service.MenuService;
import com.ying.team.service.WorkbenchInnerConnectService;
import com.ying.team.vo.MenuVO;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
        return menuService.findByMenuIdsByAuthorities(authorities);
    }

    @Override
    public Map<String,MenuVO> findMapByMenuIds(Collection<String> menuIds) {
        return menuService.findMapByIds(menuIds);
    }
}
