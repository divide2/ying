package com.ying.friend.service.impl;

import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.er.Loginer;
import com.ying.core.root.converter.Converter;
import com.ying.friend.dto.ChatDTO;
import com.ying.friend.model.Chat;
import com.ying.friend.repo.ChatRepository;
import com.ying.friend.service.ChatService;
import com.ying.friend.service.FriendConnectService;
import com.ying.friend.service.FriendInnerConnectService;
import com.ying.friend.vo.ChatVO;
import com.ying.friend.vo.FriendVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/30
 */
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    private final FriendInnerConnectService friendInnerConnectService;


    public ChatServiceImpl(ChatRepository chatRepository,
                           FriendInnerConnectService friendInnerConnectService) {
        this.chatRepository = chatRepository;
        this.friendInnerConnectService = friendInnerConnectService;
    }


    @Override
    public void add(ChatDTO dto) {

        Chat chat = chatRepository.getByFromIdAndToId(dto.getFromId(), dto.getToId());
        // 不在聊天历史里
        if (chat == null) {
            chat = new Chat();
            chat.setUnreadCount(0);
        } else {
            chat.setUnreadCount(chat.getUnreadCount() + 1);
        }
        chat.setFromId(dto.getFromId());
        chat.setToId(dto.getToId());
        chat.setLastMessage(dto.getLastMessage());
        chat.setLastTime(dto.getLastTime());
        chat.setOrderNum(0);
        chatRepository.save(chat);
    }

    @Override
    public List<ChatVO> listByUser(Integer userId) {
        List<Chat> chats = chatRepository.findByFromId(userId);
        return Converter.of(chats).convert(chat -> {
            FriendVO friend = friendInnerConnectService.getFriend(userId,chat.getToId());
            return ChatVO.builder()
                    .id(chat.getId())
                    .lastMessage(chat.getLastMessage())
                    .lastTime(chat.getLastTime())
                    .toAvatar(friend.getAvatar())
                    .memoName(friend.getMemoName())
                    .toId(chat.getToId())
                    .unreadCount(chat.getUnreadCount())
                    .build();
        });
    }
}
