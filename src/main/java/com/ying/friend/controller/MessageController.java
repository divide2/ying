package com.ying.friend.controller;

import com.ying.friend.dto.MessageDTO;
import com.ying.friend.service.MessageService;
import io.swagger.annotations.Api;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * @author bvvy
 * @date 2018/8/19
 */
@Controller
@Api(tags = "聊天")
public class MessageController {

    private final MessageService messageService;
    private final SimpUserRegistry simpUserRegistry;

    public MessageController(MessageService messageService,
                             SimpUserRegistry simpUserRegistry) {
        this.messageService = messageService;
        this.simpUserRegistry = simpUserRegistry;
    }

    @MessageMapping("/topic/send/message/{toUserId}")
    public void sendMessage(String content, @DestinationVariable Integer toUserId, Principal principal) {

        MessageDTO dto = new MessageDTO(principal.getName(), toUserId, content);
        messageService.sendMessage(dto);
    }


}
