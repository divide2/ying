package com.ying.friend.service;

import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.ConnectService;
import com.ying.friend.vo.FriendVO;
import com.ying.team.vo.MenuVO;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/1/2
 */
public interface ChatInnerConnectService extends ConnectService {


    MenuVO getMenu(String menuCode);

    List<UserVO> listTeamOwnMenuUsers(String teamId, String id);
}
