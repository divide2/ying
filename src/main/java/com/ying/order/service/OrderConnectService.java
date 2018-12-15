package com.ying.order.service;

import com.ying.core.basic.service.ConnectService;
import com.ying.friend.service.impl.FriendVO;

/**
 * @author bvvy
 * @date 2018/12/15
 */
public interface OrderConnectService extends ConnectService {
    FriendVO getOnlyFriend(Integer userId, Integer toId);
}
