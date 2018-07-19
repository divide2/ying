package com.mj.ocean.quotation.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/18 18:42
 */
@Data
@Builder
public class QuotationCostVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("报价主表id")
    private Integer quotationId;

    @ApiModelProperty("成本服务")
    private String costService;

    @ApiModelProperty("报价类别")
    private String costServiceCode;

    @ApiModelProperty("柜型，存json值  20GP，40GP，40HC，45HC")
    private String cabinetType;
}
