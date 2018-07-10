package com.mj.general.charge.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @auther: zejun
 * @date: 2018/7/10 09:33
 */
@Data
@ApiModel("费用项目修改")
public class ChargeUpdateDTO {
    @ApiModelProperty("费用项目id")
    @NotNull
    private Integer id;

    @ApiModelProperty("费用简称")
    @NotEmpty
    private String chargeItemCode;

    @ApiModelProperty("费用中文名")
    @NotEmpty
    private String chargeItemCN;

    @ApiModelProperty("费用英文名")
    @NotEmpty
    private String chargeItemEN;
}
