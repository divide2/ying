package com.ying.product.query;

import com.ying.core.root.query.QueryField;
import com.ying.core.root.query.QueryParam;
import com.ying.product.model.WarehouseProduct;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/12/10
 */
@Data
public class StockQuery implements QueryParam {

    @QueryField(entity = WarehouseProduct.class)
    private String warehouseId;
}
