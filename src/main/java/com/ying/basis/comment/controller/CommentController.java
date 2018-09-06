package com.ying.basis.comment.controller;

import com.ying.basis.comment.model.Comment;
import com.ying.basis.comment.service.CommentService;
import com.ying.basis.comment.vo.CommentVO;
import com.ying.core.er.Responser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2018/7/19
 */

@RestController
@Api("标签")
@RequestMapping("/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    @ApiOperation("全部标签")
    public ResponseEntity<List<CommentVO>> all() {
        List<Comment> all = commentService.findAll();
        List<CommentVO> vos = all.stream().map(CommentVO::of).collect(Collectors.toList());
        return Responser.ok(vos);
    }
    @GetMapping("/count/{count}")
    @ApiOperation("获取一定数量的标签")
    public ResponseEntity<List<CommentVO>> findByCount(@PathVariable Integer count) {
        return Responser.ok(null);
    }

}
