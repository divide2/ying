package com.ying.product.product.dto;

import com.ying.basis.comment.model.Comment;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/9/29
 */
@Data
public class ProductCommentAddDTO {

    public static final String COMMENT_TYPE = "PRODUCT";
    private String content;
    private Integer toId;

    /**
     * no user info
     * @return comment
     */
    public Comment to() {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCdt(LocalDateTime.now());
        comment.setToId(toId);
        comment.setType(COMMENT_TYPE);
        return comment;
    }
}
