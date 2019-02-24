package com.ying.friend.service.impl;

import com.ying.auth.vo.UserVO;
import com.ying.friend.service.ChatInnerConnectService;
import com.ying.friend.service.FriendService;
import com.ying.friend.vo.FriendVO;
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

    public ChatInnerConnectServiceImpl(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public MenuVO getMenu(String menuCode) {
        return menuService.getByCode(menuCode);
    }

    @Override
    public List<UserVO> listTeamOwnMenuUsers(String teamId, Integer menuId) {
        return null;
    }
}
