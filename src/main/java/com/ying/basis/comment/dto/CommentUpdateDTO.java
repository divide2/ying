package com.ying.basis.comment.dto;

import lombok.Data;

/**
 * @author bvvy
 * @date 2018/9/8
 */
@Data
public class CommentUpdateDTO {

    private Integer id;
    private String content;
    private String type;

}
