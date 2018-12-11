package com.ying.product.query;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 * @date 2018/12/10
 */
@Data
public class StockQuery {

    @NotEmpty
    private String warehouseType;

    @NotNull
    private Integer warehouseId;
}
