package com.ying.basis.comment.vo;

import com.ying.basis.comment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2018/7/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {


    private String content;


    public static CommentVO of(Comment comment) {
        return null;
    }

}
