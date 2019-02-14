package com.ying.friend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 * @date 2019/2/14
 */
@Data
public class ConfirmDTO {

    @NotNull
    @ApiModelProperty("对方id")
    private Integer toId;

    @ApiModelProperty("备注名")
    private String memoName;
}
