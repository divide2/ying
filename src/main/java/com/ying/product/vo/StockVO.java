package com.ying.product.vo;

import com.ying.product.model.Warehouse;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/12/11
 */
@Data
public class StockVO {

    private Warehouse warehouse;
    private ProductVO product;
    private Integer amount;
}
