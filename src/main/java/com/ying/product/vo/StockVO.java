package com.ying.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/11
 */
@Data
public class StockVO {
    private String warehouseId;
    private String warehouseName;
    private String warehouseType;
    private Integer productId;
    private String productName;
    private String[] productImage;
    private Integer productAmount;
    List<WarehouseProductSpecVO> specs;

}
