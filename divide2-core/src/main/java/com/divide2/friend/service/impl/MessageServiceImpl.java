package com.divide2.friend.service.impl;

import com.divide2.auth.vo.UserVO;
import com.divide2.core.basic.service.impl.SimpleBasicServiceImpl;
import com.divide2.friend.dto.ChatDTO;
import com.divide2.friend.dto.MessageDTO;
import com.divide2.friend.model.Message;
import com.divide2.friend.query.MessageQuery;
import com.divide2.friend.repo.MessageRepository;
import com.divide2.friend.service.FriendConnectService;
import com.divide2.friend.service.MessageInnerConnectService;
import com.divide2.friend.service.MessageService;
import com.divide2.friend.vo.FriendVO;
import com.divide2.friend.vo.MessageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public void sendMessage(MessageDTO dto) {
        UserVO toUser = friendConnectService.getUser(dto.getToId());

        Message message = Message.builder()
                .content(dto.getContent())
                .createTime(LocalDateTime.now())
                .fromId(dto.getFromId())
                .toId(toUser.getUserId())
                .readed(false)
                .build();
        messageRepository.save(message);
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setUserId(dto.getToId());
        chatDTO.setTarget(dto.getFromId().toString());
        chatDTO.setType("chat");
        chatDTO.setContent(message.getContent());
        // todo next
        messageInnerConnectService.addChat(chatDTO);
        simpMessagingTemplate.convertAndSendToUser(
                toUser.getUsername(),
                "/queue/receive/message",
                convertVO(dto.getFromId(), message));

    }

    @Override
    public Page<MessageVO> findChatMessage(Integer fromId, Integer toId, MessageQuery query, Pageable pageable) {
        Page<Message> messagePage = messageRepository.findChatMessage(fromId, toId, pageable);
        Page<MessageVO> voPage = messagePage.map(message -> convertVO(fromId, message));
        return sort(voPage);
    }

    private Page<MessageVO> sort(Page<MessageVO> voPage) {
        List<MessageVO> newContent = new ArrayList<>(voPage.getContent());
        newContent.sort((m1, m2) -> {
            if (m1.getCreateTime().isAfter(m2.getCreateTime())) {
                return 1;
            }
            if (m1.getCreateTime().isBefore(m2.getCreateTime())) {
                return -1;
            } else return 0;
        });
        return new PageImpl<>(newContent, voPage.getPageable(), voPage.getTotalElements());
    }

    private MessageVO convertVO(Integer fromId, Message message) {
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
