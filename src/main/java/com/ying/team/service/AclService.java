package com.ying.team.service;

import com.ying.team.vo.MenuVO;

import java.util.List;
import java.util.Set;

/**
 * @author bvvy
 */
public interface AclService {


    /**
     * 获取拥有菜单权限的用户id
     * @param teamId 团队
     * @param menuId 菜单
     * @return userIDs
     */
    Set<Integer> listTeamOwnMenuUserIds(String teamId, String menuId);

    List<MenuVO> listTeamUserMenus(String teamId, Integer userId);
}
