package com.ying.friend.controller;

import com.ying.core.data.del.SingleId;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.friend.dto.ApplyDTO;
import com.ying.friend.model.Message;
import com.ying.friend.service.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
@RequestMapping("/v1/friend")
public class FriendController {

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/apply")
    public ResponseEntity<Messager> apply(@RequestBody @Valid ApplyDTO dto, Errors errors) {
        friendService.apply(dto);
        return Responser.created();
    }
}
