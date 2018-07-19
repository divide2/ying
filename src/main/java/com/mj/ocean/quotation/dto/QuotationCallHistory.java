package com.mj.ocean.quotation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zejun
 * @date 2018/7/19 14:40
 */
@Data
@ApiModel("调用历史记录")
public class QuotationCallHistory {

    @ApiModelProperty("船公司id")
    @NotNull
    public Integer carrierId;

    @ApiModelProperty("起运港组合id")
    @NotNull
    public Integer portShipmentCombinationId;

    @ApiModelProperty("目的港组合id")
    @NotNull
    private Integer portDestinationCombinationId;

}
