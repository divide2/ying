package com.mj.general.charge.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/12 09:19
 */
@Data
@ApiModel("检查字段")
public class ChargeCheckDTO {

    @ApiModelProperty("费用简称")
    private String chargeItemCode;

    @ApiModelProperty("费用中文名")
    private String chargeItemCN;

    @ApiModelProperty("费用英文名")
    private String chargeItemEN;
}
