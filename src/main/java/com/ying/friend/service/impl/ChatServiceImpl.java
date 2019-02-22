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

import java.time.LocalDateTime;
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
    public void save(ChatDTO dto) {
        // 发送方
        Chat chat = chatRepository.getByUserIdAndTargetAndType(dto.getUserId(), dto.getTarget(), dto.getType());
        if (chat == null) {
            chat = new Chat();
            chat.setUnreadCount(0);
            chat.setTarget(dto.getTarget());
            chat.setUserId(dto.getUserId());
            chat.setType(dto.getType());
            chat.setAvatar(dto.getAvatar());
            chat.setName(dto.getName());
            chat.setOrderNum(0);
        } else {
            chat.setUnreadCount(chat.getUnreadCount() + 1);
        }
        chat.setContent(dto.getContent());
        chat.setUpdateTime(LocalDateTime.now());

        chatRepository.save(chat);
    }

    @Override
    public List<ChatVO> listByUser(Integer userId) {
        List<Chat> chats = chatRepository.findByUserId(userId);
        return Converter.of(chats).convert(chat -> {
            FriendVO friend = chatInnerConnectService.getFriend(userId,chat.getUserId());
            return ChatVO.builder()
                    .id(chat.getId())
                    .content(chat.getContent())
                    .updateTime(chat.getUpdateTime())
                    .avatar(friend.getAvatar())
                    .name(friend.getMemoName())
                    .toId(chat.getUserId())
                    .unreadCount(chat.getUnreadCount())
                    .build();
        });
    }
}
