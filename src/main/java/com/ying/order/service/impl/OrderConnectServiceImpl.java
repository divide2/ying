package com.ying.order.service.impl;

import com.ying.friend.service.FriendService;
import com.ying.friend.service.impl.FriendVO;
import com.ying.order.service.OrderConnectService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/12/15
 */
@Service
public class OrderConnectServiceImpl implements OrderConnectService {
    private final FriendService friendService;

    public OrderConnectServiceImpl(FriendService friendService) {
        this.friendService = friendService;
    }


    @Override
    public FriendVO getOnlyFriend(Integer fromId, Integer toId) {
        return friendService.getVO(fromId, toId);
    }

}
