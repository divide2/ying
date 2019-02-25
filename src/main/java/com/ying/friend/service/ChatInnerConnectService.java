package com.ying.friend.service;

import com.ying.core.basic.service.ConnectService;
import com.ying.team.vo.MenuVO;

import java.util.Set;

/**
 * @author bvvy
 * @date 2019/1/2
 */
public interface ChatInnerConnectService extends ConnectService {


    MenuVO getMenu(String menuCode);

    /**
     * 获取拥有菜单权限的用户ids
     * @param teamId team
     * @param menuId menuId
     * @return userIds
     */
    Set<Integer> listTeamOwnMenuUserIds(String teamId, String menuId);
}
