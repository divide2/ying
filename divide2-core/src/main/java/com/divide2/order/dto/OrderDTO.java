package com.divide2.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/17
 */
@Data
public class OrderDTO {

    @ApiModelProperty("发给的人")
    @NotEmpty
    private String toTeamId;

    @ApiModelProperty("发起的团队Id")
    @NotEmpty
    private String fromTeamId;

    @ApiModelProperty("定金")
    private BigDecimal earnestMoney;

    @ApiModelProperty("尾款")
    private BigDecimal balancePayment;

    @ApiModelProperty("交付日期")
    private LocalDate deliveryDate;

    private String remarks;

    private String attachment;

    @ApiModelProperty("商品属性价格")
    @NotEmpty
    private List<ProductSpecPrice> productSpecPrices;
}
