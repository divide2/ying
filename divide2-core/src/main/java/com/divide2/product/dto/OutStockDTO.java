package com.divide2.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/9
 */
@Data
public class OutStockDTO {
    @NotEmpty
    private String teamId;

    @NotEmpty
    private String warehouseId;

    @NotEmpty
    private String productId;

    @NotEmpty
    private List<StockUnitDTO> unit;

    @ApiModelProperty(value = "出入库方式,系统生成",hidden = true)
    private String type;

    private String remarks;

    @ApiModelProperty("产品规格的数量 amount 是正数但是是减少的")
    @NotEmpty
    private List<ProductSpecStock> specStocks;
}
