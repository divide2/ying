package com.divide2.product.bo;

import lombok.Data;

/**
 * @author bvvy
 * @date 2018/12/10
 */
@Data
public class StockBO {
    private Integer warehouseId;
    private String warehouseName;
    private String warehouseType;
    private Integer productId;
    private Integer amount;
}
