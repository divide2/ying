package com.ying.product.service.impl;

import com.ying.product.repo.WarehouseProductSpecRepository;
import com.ying.product.service.WarehouseProductSpecService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/12/27
 */
@Service
public class WarehouseProductSpecServiceImpl implements WarehouseProductSpecService {

    private final WarehouseProductSpecRepository warehouseProductSpecRepository;

    public WarehouseProductSpecServiceImpl(WarehouseProductSpecRepository warehouseProductSpecRepository) {
        this.warehouseProductSpecRepository = warehouseProductSpecRepository;
    }


}
