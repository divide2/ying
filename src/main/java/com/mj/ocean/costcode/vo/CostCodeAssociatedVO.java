package com.mj.ocean.costcode.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zejun
 * @date 2018/7/17 11:39
 */
@Data
@Builder
public class CostCodeAssociatedVO {

    @ApiModelProperty("成本关联表id")
    private Integer id;

    @ApiModelProperty("成本服务类型")
    private String costService;

    @ApiModelProperty("柜型")
    private String cabinetType;

    @ApiModelProperty("浮动金额")
    private BigDecimal floatingAmount;

    @ApiModelProperty("成本代码id")
    private Integer costCodeId;
}
