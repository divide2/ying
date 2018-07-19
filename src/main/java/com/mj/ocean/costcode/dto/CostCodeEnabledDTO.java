package com.mj.ocean.costcode.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zejun
 * @date 2018/7/17 11:25
 */
@Data
@ApiModel("启用/禁用")
public class CostCodeEnabledDTO {
    @ApiModelProperty("成本代码id")
    @NotNull
    private Integer id;

    @ApiModelProperty("启用/禁用状态")
    @NotEmpty
    private Character enabled;
}
