package com.ying.friend.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/12/30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatVO {

    private String id;

    private Integer toId;

    private String toAvatar;

    @ApiModelProperty("最后一条消息")
    private String lastMessage;

    @ApiModelProperty("最后一条消息的时间")
    private LocalDateTime lastTime;

    @ApiModelProperty("没有读过的数量")
    private Integer unreadCount;
}
