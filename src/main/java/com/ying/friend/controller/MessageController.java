package com.ying.friend.controller;

import com.ying.core.data.del.SingleStringId;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.friend.service.MessageService;
import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author bvvy
 * @date 2018/8/19
 */
@Controller
@Api(tags = "聊天")
@RequestMapping("/v1/message")
public class MessageController {

    private final MessageService messageService;
    private final SimpUserRegistry simpUserRegistry;

    public MessageController(MessageService messageService,
                             SimpUserRegistry simpUserRegistry) {
        this.messageService = messageService;
        this.simpUserRegistry = simpUserRegistry;
    }

    @DeleteMapping
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleStringId id, BindingResult br) {
        messageService.delete(id.getId());
        return Responser.deleted();
    }


}
