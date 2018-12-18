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
    @ApiModelProperty(value = "属性名称",example = "2.5寸7瓦")

    private String specName;
    @ApiModelProperty("数量")
    private Integer amount;

    private BigDecimal price;
}
