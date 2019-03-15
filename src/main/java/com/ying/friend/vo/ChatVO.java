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

    private String target;

    @ApiModelProperty("对象的名称")
    private String name;

    /**
     * 对象的类型
     */
    private String type;

    private String avatar;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("最后一条消息的时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("没有读过的数量")
    private Integer unreadCount;
}
