package com.mj.ocean.costcode.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zejun
 * @date 2018/7/17 17:05
 */
@Data
@ApiModel("查询成本代码")
public class CostCodeQueryDTO {

    @ApiModelProperty("成本代码")
    @NotEmpty
    private String code;
}
