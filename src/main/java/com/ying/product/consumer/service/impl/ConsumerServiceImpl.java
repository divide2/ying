package com.ying.product.consumer.service.impl;

import com.ying.basis.comment.service.CommentService;
import com.ying.product.consumer.service.ConsumerService;
import com.ying.product.product.dto.ProductCommentAddDTO;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/9/29
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {
    private final CommentService commentService;

    public ConsumerServiceImpl(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void addComment(ProductCommentAddDTO comment) {
        commentService.add(comment.to());
    }
}
