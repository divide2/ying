package com.ying.basis.vo;

import com.ying.basis.model.Comment;
import com.ying.core.data.Owner;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/7/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("创建时间")
    private LocalDateTime cdt;

    @ApiModelProperty("创建人信息")
    private Owner owner;

    public static CommentVO of(Comment comment) {

        CommentVO vo = new CommentVO();
        vo.setContent(comment.getContent());
        vo.setCdt(comment.getCdt());
        vo.setOwner(
                Owner.builder()
                        .id(comment.getFromId())
                        .name(comment.getFromName())
                        .avatar(comment.getFromAvatar())
                        .build()
        );
        return vo;
    }

}
