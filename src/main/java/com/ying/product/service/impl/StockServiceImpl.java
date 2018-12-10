package com.ying.product.service.impl;

import com.ying.product.dto.StockDTO;
import com.ying.product.model.Warehouse;
import com.ying.product.model.WarehouseProduct;
import com.ying.product.model.WarehouseProductSpec;
import com.ying.product.repo.WarehouseProductRepository;
import com.ying.product.repo.WarehouseProductSpecRepository;
import com.ying.product.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/9
 */
@Service
public class StockServiceImpl implements StockService {

    private final WarehouseProductRepository warehouseProductRepository;
    private final WarehouseProductSpecRepository warehouseProductSpecRepository;

    public StockServiceImpl(WarehouseProductRepository warehouseProductRepository,
                            WarehouseProductSpecRepository warehouseProductSpecRepository) {
        this.warehouseProductRepository = warehouseProductRepository;
        this.warehouseProductSpecRepository = warehouseProductSpecRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(StockDTO dto) {
        // 先存入规格的库存

        dto.getSpecStocks().forEach(specStock -> {
            WarehouseProductSpec warehouseProductSpec = new WarehouseProductSpec();
            warehouseProductSpec.setAmount(specStock.getAmount());
            warehouseProductSpec.setWarehouseId(dto.getWarehouseId());
            warehouseProductSpec.setProductId(dto.getProductId());
            warehouseProductSpec.setProductSpecId(specStock.getProductSpecId());
            warehouseProductSpecRepository.save(warehouseProductSpec);
        });
        // 再存入单个商品的库存
        WarehouseProduct warehouseProduct = new WarehouseProduct();
        warehouseProduct.setWarehouseId(dto.getWarehouseId());
        warehouseProduct.setProductId(dto.getProductId());
        warehouseProductRepository.save(warehouseProduct);
        // todo 减去消耗

        // todo 记录过程
    }
}
