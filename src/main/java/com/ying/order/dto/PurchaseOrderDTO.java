package com.ying.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
@ApiModel("采购单")
public class PurchaseOrderDTO {

    private Integer id;

    /**
     * 仓库
     */
    private Integer warehouseId;

    @ApiModelProperty("发给的人")
    private Integer toId;

    @ApiModelProperty("定金")
    private BigDecimal earnestMoney;

    @ApiModelProperty("尾款")
    private BigDecimal balancePayment;

    @ApiModelProperty("交付日期")
    private LocalDate deliveryDate;

    private String remarks;

    private String attachment;

    @ApiModelProperty("商品属性价格")
    private List<ProductSpecPrice> productSpecPrices;

}

