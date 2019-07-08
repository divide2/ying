package com.divide2.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 * @date 2019/1/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChatDTO {

    @NotNull
    private Integer userId;

    @NotEmpty
    private String target;

    @NotEmpty
    private String type;

    @NotEmpty
    private String name;

    @NotEmpty
    private String avatar;
    /**
     * 最后一条消息
     */
    @NotEmpty
    private String content;

}
