package com.ying.friend.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2019/1/2
 */
@Data
public class ChatDTO {

    @NotNull
    private Integer toId;

    @NotNull
    private Integer fromId;

    /**
     * 最后一条消息
     */
    @NotEmpty
    private String lastMessage;
    /**
     * 最后一条消息的时间
     */
    @NotNull
    private LocalDateTime lastTime;
}
