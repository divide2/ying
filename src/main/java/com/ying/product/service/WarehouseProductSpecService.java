package com.ying.product.service;

import com.ying.product.model.WarehouseProductSpec;

/**
 * @author bvvy
 * @date 2018/12/27
 */
public interface WarehouseProductSpecService {

    WarehouseProductSpec getByWarehouseAndSpec(Integer warehouseId, Integer productSpecId);
}
