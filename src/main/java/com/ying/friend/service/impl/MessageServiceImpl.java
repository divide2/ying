package com.ying.friend.service.impl;

import com.ying.auth.vo.UserVO;
import com.ying.friend.dto.ChatDTO;
import com.ying.friend.dto.MessageDTO;
import com.ying.friend.model.Chat;
import com.ying.friend.model.Message;
import com.ying.friend.repo.MessageRepository;
import com.ying.friend.service.FriendConnectService;
import com.ying.friend.service.FriendInnerConnectService;
import com.ying.friend.service.MessageService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/12/30
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageRepository messageRepository;
    private final FriendConnectService friendConnectService;
    private final FriendInnerConnectService friendInnerConnectService;

    public MessageServiceImpl(SimpMessagingTemplate simpMessagingTemplate,
                              MessageRepository messageRepository,
                              FriendConnectService friendConnectService,
                              FriendInnerConnectService friendInnerConnectService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageRepository = messageRepository;
        this.friendConnectService = friendConnectService;
        this.friendInnerConnectService = friendInnerConnectService;
    }

    @Override
    public void sendMessage(MessageDTO dto) {
        UserVO fromUser = friendConnectService.getUserByAccount(dto.getFromName());
        UserVO toUser = friendConnectService.getUser(dto.getToId());

        Message message = Message.builder()
                .content(dto.getContent())
                .createTime(LocalDateTime.now())
                .fromId(fromUser.getId())
                .toId(toUser.getId())
                .readed(false)
                .build();
        messageRepository.save(message);
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setToId(toUser.getId());
        chatDTO.setFromId(fromUser.getId());
        chatDTO.setLastMessage(message.getContent());
        chatDTO.setLastTime(message.getCreateTime());
        friendInnerConnectService.addChat(chatDTO);
        simpMessagingTemplate.convertAndSendToUser(toUser.getUsername(), "/queue/receive/message", dto.getContent());

    }
}
