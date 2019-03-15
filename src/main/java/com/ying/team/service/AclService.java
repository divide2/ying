package com.ying.team.service;

import com.ying.team.dto.AclDTO;

import java.util.Set;

/**
 * @author bvvy
 */
public interface AclService {


    /**
     * 获取拥有菜单权限的用户id
     * @param teamId 团队
     * @param authority 菜单
     * @return userIDs
     */
    Set<Integer> listTeamOwnMenuUserIds(String teamId, String authority);

    /**
     * 获取用户能访问的菜单的权限码包括所在的squad下的菜单
     * @param teamId teanUd
     * @param userId userId
     * @return menu
     */
    Set<String> listTeamUserAuthorities(String teamId, Integer userId);

    /**
     * 添加权限
     * @param dto dto
     */
    void add(AclDTO dto);

    Set<String> listOnlyTeamUserAuthorities(String teamId, Integer userId);

    Set<String> listOnlyTeamSquadAuthorities(String teamId, String squadId);
}
