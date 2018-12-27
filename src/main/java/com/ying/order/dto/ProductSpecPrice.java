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
    private Integer orderId;

    private Integer productId;

    @ApiModelProperty(value = "产品属性关联表id")
    private Integer productSpecId;

    @ApiModelProperty("数量")
    private Integer amount;

    private BigDecimal price;
}
