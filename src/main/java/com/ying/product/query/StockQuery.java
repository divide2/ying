package com.ying.product.query;

import com.ying.core.root.query.QueryField;
import com.ying.core.root.query.QueryParam;
import com.ying.product.model.Warehouse;
import com.ying.product.model.WarehouseProduct;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 * @date 2018/12/10
 */
@Data
public class StockQuery implements QueryParam {

    @QueryField(entity = Warehouse.class,value = "type")
    private String warehouseType;

    @QueryField(entity = WarehouseProduct.class)
    private Integer warehouseId;
}
