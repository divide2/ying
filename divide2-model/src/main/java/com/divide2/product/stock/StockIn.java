package com.divide2.product.stock;

import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/4/18
 */
@Data
public class StockIn {

    private String teamId;
    private String warehouseId;
    private List<StockProduct> stockProducts;

}
