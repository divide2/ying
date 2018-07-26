package com.mj.ocean.quotation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/17 19:04
 */
@Data
@ApiModel("新增常规报价关联表")
public class QuotationCostAddDTO {

    @ApiModelProperty("船东成本价")
    public String originalPrice;

    @ApiModelProperty("商务成本价")
    public String commercePrice;

    @ApiModelProperty("业务成本价")
    public String businessPrice;

    @ApiModelProperty("公开展示价")
    public String openPrice;

    @ApiModelProperty("柜型")
    public String type;
}
