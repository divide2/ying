package com.divide2.product.query;

import com.divide2.core.root.query.QueryField;
import com.divide2.core.root.query.QueryParam;
import com.divide2.product.model.Stock;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/12/10
 */
@Data
public class StockQuery implements QueryParam {

    @QueryField(entity = Stock.class)
    private String warehouseId;
}
