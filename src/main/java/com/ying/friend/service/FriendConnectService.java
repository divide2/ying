package com.ying.friend.service;

import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.ConnectService;

/**
 * @author bvvy
 * @date 2018/12/15
 */
public interface FriendConnectService extends ConnectService {

    UserVO getUser(Integer toId);
}
