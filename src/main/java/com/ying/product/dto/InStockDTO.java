package com.ying.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InStockDTO {

    @NotEmpty
    private String teamId;
    @NotEmpty
    private String warehouseId;
    @NotEmpty
    private String productId;
    @ApiModelProperty("产品规格的数量 amount是正数 是增加的")
    @NotEmpty
    private List<ProductSpecStock> specStocks;

    @ApiModelProperty(value = "出入库方式,系统生成",hidden = true)
    private String type;

    @ApiModelProperty("单位数量")
    private List<StockUnitDTO> unit;

    @ApiModelProperty("消耗的配件,也就是采购的商品,不输入代表没有消耗")
    private List<ProductDepletion> depletions;
}
