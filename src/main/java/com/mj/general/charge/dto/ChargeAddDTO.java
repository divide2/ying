package com.mj.general.charge.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author zejun
 * @date 2018/7/10 09:30
 */
@Data
@ApiModel("费用项目新增")
public class ChargeAddDTO {

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
