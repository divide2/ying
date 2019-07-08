package com.divide2.friend.service.impl;

import com.divide2.friend.service.ChatInnerConnectService;
import com.divide2.team.service.AclService;
import com.divide2.team.service.MenuService;
import com.divide2.team.vo.MenuVO;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author bvvy
 * @date 2019/1/2
 */
@Service
public class ChatInnerConnectServiceImpl implements ChatInnerConnectService {
    private final MenuService menuService;
    private final AclService aclService;

    public ChatInnerConnectServiceImpl(MenuService menuService,
                                       AclService aclService) {
        this.menuService = menuService;
        this.aclService = aclService;
    }

    @Override
    public MenuVO getMenu(String authority) {
        return menuService.getByAuthority(authority);
    }

    @Override
    public Set<Integer> listTeamOwnMenuUserIds(String teamId, String menuId) {
        return aclService.listTeamOwnMenuUserIds(teamId, menuId);
    }
}
