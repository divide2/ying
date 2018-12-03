package com.ying.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
public class ProductSpecPrice {

    private Integer productId;
    @ApiModelProperty(value = "属性名称",example = "2.5寸7瓦")
    private String specName;
    @ApiModelProperty("数量")
    private Integer amount;
    private BigDecimal price;
}
