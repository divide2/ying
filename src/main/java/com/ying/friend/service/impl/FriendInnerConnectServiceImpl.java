package com.ying.friend.service.impl;

import com.ying.friend.dto.ChatDTO;
import com.ying.friend.service.ChatService;
import com.ying.friend.service.FriendInnerConnectService;
import com.ying.friend.service.FriendService;
import com.ying.friend.vo.FriendVO;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2019/1/2
 */
@Service
public class FriendInnerConnectServiceImpl implements FriendInnerConnectService {
    private final FriendService friendService;
    private final ChatService chatService;

    public FriendInnerConnectServiceImpl(FriendService friendService, ChatService chatService) {
        this.friendService = friendService;
        this.chatService = chatService;
    }

    @Override
    public FriendVO getFriend(Integer fromId, Integer toId) {
        return friendService.getVO(fromId, toId);
    }

    @Override
    public void addChat(ChatDTO chatDTO) {
        chatService.add(chatDTO);
    }
}
