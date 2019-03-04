package com.ying.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/11
 */
@Data
public class StockVO {
    private String id;
    private String name;
    private String[] image;
    private Integer amount;
    List<WarehouseProductSpecVO> specs;

}
