package com.ying.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
public class ProductSpecPrice {

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private String orderId;

    private String productId;

    @ApiModelProperty(value = "产品属性关联表id")
    private String productSpecId;

    @ApiModelProperty("数量")
    private Integer amount;

    @ApiModelProperty("单位")
    private String unit;


    private BigDecimal price;
}
