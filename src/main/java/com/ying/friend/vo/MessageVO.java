package com.ying.friend.vo;

import com.ying.core.root.VO;
import com.ying.friend.model.Message;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2019/1/2
 */
@Data
public class MessageVO implements VO<Message> {
    private String id;

    @ApiModelProperty("给的人的头像")
    private String toAvatar;

    @ApiModelProperty("备注名")
    private String memoName;

    @ApiModelProperty("给的人")
    private Integer toId;

    @ApiModelProperty("消息内容")
    private String content;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("读过么")
    private Boolean readed;
}
