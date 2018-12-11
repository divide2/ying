package com.ying.product.service.impl;

import com.ying.core.er.Loginer;
import com.ying.product.dto.ProductSpecStock;
import com.ying.product.dto.StockDTO;
import com.ying.product.model.*;
import com.ying.product.query.StockQuery;
import com.ying.product.repo.ProductRepository;
import com.ying.product.repo.ProductSpecRepository;
import com.ying.product.repo.WarehouseProductRepository;
import com.ying.product.repo.WarehouseProductSpecRepository;
import com.ying.product.service.StockService;
import com.ying.product.bo.StockBO;
import com.ying.product.vo.ProductVO;
import com.ying.product.vo.StockVO;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2018/12/9
 */
@Service
public class StockServiceImpl implements StockService {

    private final WarehouseProductRepository warehouseProductRepository;
    private final WarehouseProductSpecRepository warehouseProductSpecRepository;
    private final ProductSpecRepository productSpecRepository;
    private final ProductRepository productRepository;

    public StockServiceImpl(WarehouseProductRepository warehouseProductRepository,
                            WarehouseProductSpecRepository warehouseProductSpecRepository,
                            ProductSpecRepository productSpecRepository,
                            ProductRepository productRepository) {
        this.warehouseProductRepository = warehouseProductRepository;
        this.warehouseProductSpecRepository = warehouseProductSpecRepository;
        this.productSpecRepository = productSpecRepository;

        this.productRepository = productRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(StockDTO dto) {
        // 先存入规格的库存
        dto.getSpecStocks().forEach(specStock -> {
            WarehouseProductSpec warehouseProductSpec = warehouseProductSpecRepository.getByAllId(
                    dto.getWarehouseId(), dto.getProductId(), specStock.getProductSpecId());
            if (warehouseProductSpec == null) {
                warehouseProductSpec = new WarehouseProductSpec();
            }
            ProductSpec productSpec = productSpecRepository.getOne(specStock.getProductSpecId());
            warehouseProductSpec.setAmount(specStock.getAmount());
            warehouseProductSpec.setWarehouseId(dto.getWarehouseId());
            warehouseProductSpec.setProductId(dto.getProductId());
            warehouseProductSpec.setProductSpecId(specStock.getProductSpecId());
            warehouseProductSpec.setProductSpecName(productSpec.getName());
            warehouseProductSpecRepository.save(warehouseProductSpec);
        });
        // 再存入单个商品的库存
        Optional<Integer> totalAmount = dto.getSpecStocks().stream().map(ProductSpecStock::getAmount).reduce((a, b) -> a + b);
        WarehouseProduct warehouseProduct = warehouseProductRepository
                .getByWarehouseIdAndProductId(dto.getWarehouseId(), dto.getProductId());
        if (warehouseProduct == null) {
            warehouseProduct = new WarehouseProduct();
            warehouseProduct.setAmount(totalAmount.orElse(0));
        } else{
            warehouseProduct.setAmount(warehouseProduct.getAmount() + totalAmount.orElse(0));

        }
        warehouseProduct.setWarehouseId(dto.getWarehouseId());
        warehouseProduct.setLastTime(LocalDateTime.now());
        warehouseProduct.setProductId(dto.getProductId());
        warehouseProductRepository.save(warehouseProduct);
        // todo 减去消耗

        // todo 记录过程
    }

    @Override
    public Page<StockVO> findByCompany(StockQuery stockQuery, Pageable pageable) {
        Page<StockBO> boPage = warehouseProductRepository.findByCompany(Loginer.companyId(), stockQuery, pageable);
        List<Integer> productIds = boPage.getContent().stream().map(StockBO::getProductId).collect(Collectors.toList());
        Map<Integer, Product> productMap = productRepository.findByIds(productIds);
        return boPage.map(bo -> {
            Product product = productMap.get(bo.getProductId());
            ProductVO productVO = ProductVO.of(product);
            StockVO stockVO = new StockVO();
            stockVO.setProduct(productVO);
            Warehouse warehouse = new Warehouse();
            warehouse.setName(bo.getWarehouseName());
            warehouse.setId(bo.getWarehouseId());
            warehouse.setType(bo.getWarehouseType());
            stockVO.setWarehouse(warehouse);
            stockVO.setAmount(bo.getAmount());
            return stockVO;
        });
    }

    @Override
    public Page<StockBO> find(StockQuery stockQuery) {
        return null;
    }
}
