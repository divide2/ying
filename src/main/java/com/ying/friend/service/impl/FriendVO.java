package com.ying.friend.service.impl;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/12/11
 */
@Data
public class FriendVO {

    private Integer toId;

    @ApiModelProperty("备注名")
    private String memoName;

    private String avatar;

}
