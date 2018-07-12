package com.mj.general.route.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zejun
 * @date 2018/7/12 11:19
 */
@Data
@ApiModel("禁/启用航线")
public class RouteEnabledDTO {
    @ApiModelProperty("航线id")
    @NotNull
    private Integer id;

    @ApiModelProperty("禁用状态")
    @NotEmpty
    private String status;
}
