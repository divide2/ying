package com.ying.friend.service;

import com.ying.core.basic.service.ConnectService;
import com.ying.friend.vo.FriendVO;

/**
 * @author bvvy
 * @date 2019/1/2
 */
public interface ChatInnerConnectService extends ConnectService {

    FriendVO getFriend(Integer fromId, Integer toId);

}
