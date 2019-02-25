package com.ying.team.service;

import com.ying.auth.vo.UserVO;
import com.ying.team.dto.RolePerAddDTO;
import com.ying.team.vo.MenuVO;

import java.util.List;

/**
 * @author bvvy
 */
public interface AclService {


    List<UserVO> listTeamOwnMenuUsers(String teamId, String menuId);

    List<MenuVO> listTeamUserMenus(String teamId, Integer userId);
}
