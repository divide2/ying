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

    @ApiModelProperty("成本服务")
    public String costService;

    @ApiModelProperty("报价类别")
    public String costServiceCode;

    @ApiModelProperty("柜型")
    public String cabinetType;
}
