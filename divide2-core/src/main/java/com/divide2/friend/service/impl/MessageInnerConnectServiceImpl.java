package com.divide2.friend.service.impl;

import com.divide2.friend.dto.ChatDTO;
import com.divide2.friend.service.ChatService;
import com.divide2.friend.service.FriendService;
import com.divide2.friend.service.MessageInnerConnectService;
import com.divide2.friend.vo.FriendVO;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2019/1/2
 */
@Service
public class MessageInnerConnectServiceImpl implements MessageInnerConnectService {

    private final ChatService chatService;
    private final FriendService friendService;

    public MessageInnerConnectServiceImpl(ChatService chatService,
                                          FriendService friendService) {
        this.chatService = chatService;
        this.friendService = friendService;
    }

    @Override
    public void addChat(ChatDTO chatDTO) {
        chatService.save(chatDTO);
    }

    @Override
    public FriendVO getFriend(Integer fromId, Integer toId) {
        return friendService.getVO(fromId, toId);
    }

}
