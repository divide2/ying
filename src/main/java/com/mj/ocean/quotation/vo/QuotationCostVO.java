package com.mj.ocean.quotation.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @author zejun
 * @date 2018/7/18 18:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuotationCostVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("报价主表id")
    private Integer quotationId;

    @ApiModelProperty("船东成本价")
    private String originalPrice;

    @ApiModelProperty("商务成本价")
    private String commercePrice;

    @ApiModelProperty("业务成本价")
    private String businessPrice;

    @ApiModelProperty("公开展示价")
    private String openPrice;

    @ApiModelProperty("柜型，20GP，40GP，40HC，45HC")
    private String type;
}
