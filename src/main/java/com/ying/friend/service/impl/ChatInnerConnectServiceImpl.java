package com.ying.friend.service.impl;

import com.ying.friend.service.ChatInnerConnectService;
import com.ying.friend.service.FriendService;
import com.ying.friend.vo.FriendVO;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2019/1/2
 */
@Service
public class ChatInnerConnectServiceImpl implements ChatInnerConnectService {
    private final FriendService friendService;

    public ChatInnerConnectServiceImpl(FriendService friendService) {
        this.friendService = friendService;
    }

    @Override
    public FriendVO getFriend(Integer fromId, Integer toId) {
        return friendService.getVO(fromId, toId);
    }

}
