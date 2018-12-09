package com.ying.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/9
 */
@Data
public class StockDTO {
    private Integer warehouseId;
    private Integer productId;
    @ApiModelProperty("产品规格的数量")
    private List<ProductSpecStock> specStocks;

    @ApiModelProperty("消耗的配件,也就是采购的商品,不输入代表没有消耗")
    private List<ProductDepletion> depletions;
}
