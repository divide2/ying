package com.ying.friend.service.impl;

import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.friend.dto.ChatDTO;
import com.ying.friend.dto.MessageDTO;
import com.ying.friend.model.Message;
import com.ying.friend.query.MessageQuery;
import com.ying.friend.repo.MessageRepository;
import com.ying.friend.service.FriendConnectService;
import com.ying.friend.service.MessageInnerConnectService;
import com.ying.friend.service.MessageService;
import com.ying.friend.vo.FriendVO;
import com.ying.friend.vo.MessageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/12/30
 */
@Service
public class MessageServiceImpl extends SimpleBasicServiceImpl<Message, String, MessageRepository> implements MessageService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageRepository messageRepository;
    private final FriendConnectService friendConnectService;
    private final MessageInnerConnectService messageInnerConnectService;

    public MessageServiceImpl(SimpMessagingTemplate simpMessagingTemplate,
                              MessageRepository messageRepository,
                              FriendConnectService friendConnectService,
                              MessageInnerConnectService messageInnerConnectService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageRepository = messageRepository;
        this.friendConnectService = friendConnectService;
        this.messageInnerConnectService = messageInnerConnectService;
    }

    @Override
    @Transactional
    public void sendMessage( MessageDTO dto) {
        UserVO toUser = friendConnectService.getUser(dto.getToId());

        Message message = Message.builder()
                .content(dto.getContent())
                .createTime(LocalDateTime.now())
                .fromId(dto.getFromId())
                .toId(toUser.getId())
                .readed(false)
                .build();
        messageRepository.save(message);
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setToId(dto.getToId());
        chatDTO.setFromId(dto.getFromId());
        chatDTO.setLastMessage(message.getContent());
        chatDTO.setLastTime(message.getCreateTime());
        messageInnerConnectService.addChat(chatDTO);
        simpMessagingTemplate.convertAndSendToUser(
                toUser.getUsername(),
                "/queue/receive/message",
                convertVO(dto.getFromId(),message));

    }

    @Override
    public Page<MessageVO> findChatMessage(Integer fromId, Integer toId, MessageQuery query, Pageable pageable) {
        Page<Message> messagePage = messageRepository.findChatMessage(fromId, toId, pageable);
        return messagePage.map(message -> convertVO(fromId, message));
    }

    private MessageVO convertVO(Integer fromId,Message message) {
        FriendVO friend = messageInnerConnectService.getFriend(message.getFromId(), message.getToId());
        MessageVO vo = new MessageVO();
        vo.setId(message.getId());
        vo.setContent(message.getContent());
        vo.setCreateTime(message.getCreateTime());
        vo.setMemoName(friend.getMemoName());
        vo.setReaded(message.getReaded());
        UserVO user = friendConnectService.getUser(message.getFromId());
        vo.setFromAvatar(user.getAvatar());
        vo.setToId(message.getToId());
        vo.setBySelf(message.getFromId().equals(fromId));
        return vo;
    }
}
