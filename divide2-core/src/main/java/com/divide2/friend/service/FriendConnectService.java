package com.divide2.friend.service;

import com.divide2.auth.vo.UserVO;
import com.divide2.core.basic.service.ConnectService;
import com.divide2.friend.dto.SimpleMenuChatDTO;

/**
 * @author bvvy
 * @date 2018/12/15
 */
public interface FriendConnectService extends ConnectService {

    UserVO getUser(Integer userId);

    UserVO getUserByAccount(String account);

    void addChat(SimpleMenuChatDTO dto);
}
