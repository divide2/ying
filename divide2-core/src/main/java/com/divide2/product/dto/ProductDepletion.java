package com.divide2.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/12/9
 */
@Data
@ApiModel("入库的时候消耗的配件 采购商品")
public class ProductDepletion {

    @ApiModelProperty("消耗的产品id")
    private String productId;

    @ApiModelProperty("消耗的数量")
    private Integer amount;
}

