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

    @ApiModelProperty("朋友的id")
    private Integer toId;

    @ApiModelProperty("备注名")
    private String memoName;

    @ApiModelProperty("朋友公司")
    private String companyId;

    @ApiModelProperty("朋友名称")
    private String companyName;

    private String avatar;

}
