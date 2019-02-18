package com.ying.mine.controller;

import com.ying.auth.vo.UserGroupVO;
import com.ying.basis.model.Comment;
import com.ying.basis.model.Star;
import com.ying.basis.service.CommentService;
import com.ying.basis.service.StarService;
import com.ying.basis.vo.CommentVO;
import com.ying.basis.vo.StarVO;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.friend.dto.MessageDTO;
import com.ying.friend.query.MessageQuery;
import com.ying.friend.vo.ChatVO;
import com.ying.friend.vo.FriendVO;
import com.ying.friend.vo.MessageVO;
import com.ying.mine.service.MineService;
import com.ying.mine.vo.WarehouseVO;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.vo.OrderVO;
import com.ying.product.query.StockQuery;
import com.ying.product.vo.ProductVO;
import com.ying.product.vo.StockVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.BindingType;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/9/6
 */
@RequestMapping("/v1/mine")
@RestController
@Api(tags = "我的")
public class MineController {

    private final MineService mineService;
    private final StarService starService;
    private final CommentService commentService;

    public MineController(MineService mineService,
                          StarService starService,
                          CommentService commentService) {
        this.mineService = mineService;
        this.starService = starService;
        this.commentService = commentService;
    }

    @GetMapping("/groups")
    public ResponseEntity<List<UserGroupVO>> findGroup() {
        List<UserGroupVO> vos = mineService.listUserGroup();
        return Responser.ok(vos);
    }

    @GetMapping("/group/{groupId}/authorities")
    public ResponseEntity<List<String>> listAuthorities(@PathVariable String groupId) {
        List<String> authorities = mineService.listAuthorities(groupId);
        return Responser.ok(authorities);
    }

    @PostMapping("/message")
    public ResponseEntity<Messager> sendMessage(@RequestBody @Valid MessageDTO messageDTO, Errors errors) {
        mineService.sendMessage(messageDTO);
        return Responser.created();
    }

    @GetMapping("/{toUserId}/messages")
    public ResponseEntity<Page<MessageVO>> findMessage(@PathVariable Integer toUserId, MessageQuery query, Pageable pageable) {
        Page<MessageVO> vos = mineService.findMessage(toUserId,query, pageable);
        return Responser.ok(vos);
    }

    @GetMapping("/chats")
    public ResponseEntity<List<ChatVO>> listChat() {
        List<ChatVO> chats = mineService.listChat();
        return Responser.ok(chats);
    }

    @GetMapping("/friends")
    @ApiOperation("我的好友")
    public ResponseEntity<List<FriendVO>> friends() {
        List<FriendVO> vos = mineService.listFriends();
        return Responser.ok(vos);
    }

    @GetMapping("/friend/{friendId}")
    @ApiOperation("我的好友")
    public ResponseEntity<FriendVO> friends(@PathVariable Integer friendId) {
        FriendVO vo = mineService.getFriend(friendId);
        return Responser.ok(vo);
    }


    @GetMapping("/stars")
    @ApiOperation("我的star")
    public ResponseEntity<Page<StarVO>> star(Pageable pageable) {

        Page<Star> stars = starService.findByUser(pageable);
        return Responser.ok(stars.map(StarVO::of));
    }

    @GetMapping("/comments")
    @ApiOperation("我的评论")
    public ResponseEntity<Page<CommentVO>> comments(Pageable pageable) {
        Page<Comment> comments = commentService.findByUser(pageable);
        return Responser.ok(comments.map(CommentVO::of));
    }
}
