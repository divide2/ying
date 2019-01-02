package com.ying.friend.service.impl;

import com.ying.core.root.converter.Converter;
import com.ying.friend.dto.ChatDTO;
import com.ying.friend.model.Chat;
import com.ying.friend.repo.ChatRepository;
import com.ying.friend.service.ChatService;
import com.ying.friend.service.ChatInnerConnectService;
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

    private final ChatInnerConnectService chatInnerConnectService;


    public ChatServiceImpl(ChatRepository chatRepository,
                           ChatInnerConnectService chatInnerConnectService) {
        this.chatRepository = chatRepository;
        this.chatInnerConnectService = chatInnerConnectService;
    }


    @Override
    public void add(ChatDTO dto) {
        // 发送方
        Chat from = chatRepository.getByFromIdAndToId(dto.getFromId(), dto.getToId());
        if (from == null) {
            from = new Chat();
            from.setUnreadCount(0);
        }
        from.setFromId(dto.getFromId());
        from.setToId(dto.getToId());
        from.setLastMessage(dto.getLastMessage());
        from.setLastTime(dto.getLastTime());
        from.setOrderNum(0);
        chatRepository.save(from);
        // 接收方
        Chat to = chatRepository.getByFromIdAndToId(dto.getToId(), dto.getFromId());
        if (to == null) {
            to = new Chat();
            to.setUnreadCount(1);
        } else {
            to.setUnreadCount(to.getUnreadCount() + 1);
        }
        to.setFromId(dto.getToId());
        to.setToId(dto.getFromId());
        to.setLastMessage(dto.getLastMessage());
        to.setLastTime(dto.getLastTime());
        to.setOrderNum(0);
        chatRepository.save(to);
    }

    @Override
    public List<ChatVO> listByUser(Integer userId) {
        List<Chat> chats = chatRepository.findByFromId(userId);
        return Converter.of(chats).convert(chat -> {
            FriendVO friend = chatInnerConnectService.getFriend(userId,chat.getToId());
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
