package com.ying.product.service.impl;

import com.ying.product.model.WarehouseProductSpec;
import com.ying.product.repo.WarehouseProductSpecRepository;
import com.ying.product.service.WarehouseProductSpecService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/12/27
 */
@Service
public class WarehouseProductSpecServiceImpl implements WarehouseProductSpecService  {

    private final WarehouseProductSpecRepository warehouseProductSpecRepository;

    public WarehouseProductSpecServiceImpl(WarehouseProductSpecRepository warehouseProductSpecRepository) {
        this.warehouseProductSpecRepository = warehouseProductSpecRepository;
    }

    @Override
    public WarehouseProductSpec getByWarehouseAndSpec(Integer warehouseId, Integer productSpecId) {
        return warehouseProductSpecRepository.getByWarehouseIdAndProductSpecId(warehouseId, productSpecId);
    }
}
