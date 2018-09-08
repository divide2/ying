package com.ying.basis.comment.controller;

import com.ying.basis.comment.dto.CommentAddDTO;
import com.ying.basis.comment.dto.CommentQueryDTO;
import com.ying.basis.comment.dto.CommentUpdateDTO;
import com.ying.basis.comment.model.Comment;
import com.ying.basis.comment.service.CommentService;
import com.ying.core.data.del.SingleId;
import com.ying.core.data.properties.StatusProperties;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@RestController
@RequestMapping("/v1/comment")
@Api(tags = "文章")
public class CommentController {

    private final StatusProperties statusProperties;
    private final CommentService commentService;

    public CommentController(StatusProperties statusProperties,
                             CommentService commentService) {
        this.statusProperties = statusProperties;
        this.commentService = commentService;
    }

    @PostMapping
    @ApiOperation("添加")
    public ResponseEntity<Messager> add(@Valid @RequestBody CommentAddDTO commentAddDTO, BindingResult br) {
        commentService.add(null);
        return Responser.created();
    }

    @PatchMapping
    @ApiOperation("修改")
    public ResponseEntity<Messager> update(@Valid @RequestBody CommentUpdateDTO commentUpdateDTO, BindingResult br) {
        commentService.update(null);
        return Responser.updated();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleId id, BindingResult br) {
        commentService.delete(id.getId());
        return Responser.deleted();
    }

    @GetMapping("/{id}")
    @ApiOperation("获取一篇文章")
    public ResponseEntity<CommentVO> get(@PathVariable Integer id) {
        Comment comment = commentService.get(id);
        CommentVO vo = CommentVO.fromComment(comment);
        return Responser.ok(vo);
    }

    @GetMapping("/find")
    @ApiOperation("获取全部文章已发布的")
    public ResponseEntity<Page<CommentVO>> find(CommentQueryDTO commentQueryDTO, Pageable pageable) {
        Page<Comment> comments = commentService.find( pageable);
        Page<CommentVO> vos = comments.map(CommentVO::fromComment);
        return Responser.ok(vos);
    }

/*
    @GetMapping("/find/own/{publishStatus}")
    @ApiOperation("获取我的全部文章")
    public ResponseEntity<Page<CommentVO>> findSelf(@PathVariable String publishStatus, CommentQueryDTO commentQueryDTO, Pageable pageable) {
        Page<Comment> comments = commentService.findOwn(publishStatus, commentQueryDTO, pageable);
        Page<CommentVO> vos = comments.map(CommentVO::fromComment);
        return Responser.ok(vos);
    }
*/

}
