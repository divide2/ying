package com.mj.general.charge.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/10 09:20
 */
@Data
@Builder
public class ChargeVO {

    @ApiModelProperty("费用项目id")
    private Integer id;

    @ApiModelProperty("费用简称")
    private String chargeItemCode;

    @ApiModelProperty("费用中文名")
    private String chargeItemCN;

    @ApiModelProperty("费用英文名")
    private String chargeItemEN;

    @ApiModelProperty("禁用状态")
    private Character enabled;

}
