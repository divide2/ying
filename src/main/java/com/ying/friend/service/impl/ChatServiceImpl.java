package com.ying.friend.service.impl;

import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.root.converter.Converter;
import com.ying.friend.model.Chat;
import com.ying.friend.repo.ChatRepository;
import com.ying.friend.service.ChatService;
import com.ying.friend.service.FriendConnectService;
import com.ying.friend.vo.ChatVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/30
 */
@Service
public class ChatServiceImpl extends SimpleBasicServiceImpl<Chat,String,ChatRepository> implements ChatService {

    private final ChatRepository chatRepository;

    private final FriendConnectService friendConnectService;

    public ChatServiceImpl(ChatRepository chatRepository,
                           FriendConnectService friendConnectService) {
        this.chatRepository = chatRepository;
        this.friendConnectService = friendConnectService;
    }

    public List<ChatVO> listByUser(Integer userId) {
        List<Chat> chats = chatRepository.findByFromId(userId);
        return Converter.of(chats).convert(chat -> {
            UserVO user = friendConnectService.getUser(chat.getToId());
            return ChatVO.builder()
                    .id(chat.getId())
                    .lastMessage(chat.getLastMessage())
                    .lastTime(chat.getLastTime())
                    .toAvatar(user.getAvatar())
                    .toId(chat.getToId())
                    .unreadCount(chat.getUnreadCount())
                    .build();
        });
    }
}
