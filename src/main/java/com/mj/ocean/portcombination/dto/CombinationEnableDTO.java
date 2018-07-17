package com.mj.ocean.portcombination.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zejun
 * @date 2018/7/13 17:50
 */
@Data
@ApiModel("禁/启用状态")
public class CombinationEnableDTO {
    @ApiModelProperty("港口组id")
    @NotNull
    private Integer id;

    @ApiModelProperty("禁/启用状态")
    @NotEmpty
    private Character enabled;
}
