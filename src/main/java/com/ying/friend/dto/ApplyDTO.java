package com.ying.friend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 * @date 2019/2/14
 */
@Data
public class ApplyDTO {

    @NotNull
    @ApiModelProperty("对方id")
    private Integer toId;

    @ApiModelProperty("对方的备注")
    private String memoName;

    @ApiModelProperty("备注信息")
    private String remarks;
}
