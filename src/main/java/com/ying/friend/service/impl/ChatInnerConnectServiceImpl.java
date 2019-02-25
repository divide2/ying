package com.ying.friend.service.impl;

import com.ying.auth.vo.UserVO;
import com.ying.friend.service.ChatInnerConnectService;
import com.ying.team.service.AclService;
import com.ying.team.service.MenuService;
import com.ying.team.vo.MenuVO;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public MenuVO getMenu(String menuCode) {
        return menuService.getByCode(menuCode);
    }

    @Override
    public List<UserVO> listTeamOwnMenuUsers(String teamId, Integer menuId) {
        return aclService.listTeamOwnMenuUsers(teamId, menuId);
    }
}
