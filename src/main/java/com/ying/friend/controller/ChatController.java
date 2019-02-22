package com.ying.friend.controller;

import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.friend.dto.ChatDTO;
import com.ying.friend.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author bvvy
 * @date 2018/12/30
 */
@RestController
@RequestMapping("/v1/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<Messager> add(@RequestBody @Valid ChatDTO dto, BindingResult br) {
        chatService.save(dto);
        return Responser.created();
    }
}
