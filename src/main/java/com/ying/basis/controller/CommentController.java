package com.ying.basis.controller;

import com.ying.basis.dto.CommentAddDTO;
import com.ying.basis.dto.CommentQueryDTO;
import com.ying.basis.dto.CommentUpdateDTO;
import com.ying.basis.model.Comment;
import com.ying.basis.service.CommentService;
import com.ying.basis.vo.CommentVO;
import com.ying.core.data.del.SingleId;
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
@Api(tags = "评论")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ApiOperation("添加")
    public ResponseEntity<Messager> add(@Valid @RequestBody CommentAddDTO add, BindingResult br) {
        commentService.add(add.to());
        return Responser.created();
    }

    @PatchMapping
    @ApiOperation("修改")
    public ResponseEntity<Messager> update(@Valid @RequestBody CommentUpdateDTO update, BindingResult br) {
        Comment comment = commentService.get(update.getId());
        comment.setContent(update.getContent());
        commentService.update(comment);
        return Responser.updated();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleId id, BindingResult br) {
        commentService.delete(id.getId());
        return Responser.deleted();
    }

    @GetMapping("/{id}")
    @ApiOperation("获取一条评论")
    public ResponseEntity<CommentVO> get(@PathVariable Integer id) {
        Comment comment = commentService.get(id);
        CommentVO vo = CommentVO.of(comment);
        return Responser.ok(vo);
    }

    @GetMapping("/find")
    @ApiOperation("获取全部评论")
    public ResponseEntity<Page<CommentVO>> find(CommentQueryDTO commentQueryDTO, Pageable pageable) {
        Page<Comment> comments = commentService.find( pageable);
        Page<CommentVO> vos = comments.map(CommentVO::of);
        return Responser.ok(vos);
    }


}
