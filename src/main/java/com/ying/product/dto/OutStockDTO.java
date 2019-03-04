package com.ying.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/9
 */
@Data
public class OutStockDTO {
    @NotNull
    private String warehouseId;

    @NotNull
    private String productId;

    @ApiModelProperty("产品规格的数量 amount 是正数但是是减少的")
    @NotEmpty
    private List<ProductSpecStock> specStocks;
}
