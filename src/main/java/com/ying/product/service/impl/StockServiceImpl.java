package com.ying.product.service.impl;

import com.ying.core.exception.ValidationException;
import com.ying.core.root.converter.Converter;
import com.ying.product.bo.StockBO;
import com.ying.product.dto.InStockDTO;
import com.ying.product.dto.OutStockDTO;
import com.ying.product.dto.ProductSpecStock;
import com.ying.product.model.ProductSpec;
import com.ying.product.model.WarehouseProduct;
import com.ying.product.model.WarehouseProductSpec;
import com.ying.product.query.StockQuery;
import com.ying.product.repo.ProductRepository;
import com.ying.product.repo.ProductSpecRepository;
import com.ying.product.repo.WarehouseProductRepository;
import com.ying.product.repo.WarehouseProductSpecRepository;
import com.ying.product.service.ProductInnerCollectService;
import com.ying.product.service.StockService;
import com.ying.product.vo.StockVO;
import com.ying.product.vo.WarehouseProductSpecVO;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
    private final ProductInnerCollectService productInnerCollectService;

    public StockServiceImpl(WarehouseProductRepository warehouseProductRepository,
                            WarehouseProductSpecRepository warehouseProductSpecRepository,
                            ProductSpecRepository productSpecRepository,
                            ProductRepository productRepository, ProductInnerCollectService productInnerCollectService) {
        this.warehouseProductRepository = warehouseProductRepository;
        this.warehouseProductSpecRepository = warehouseProductSpecRepository;
        this.productSpecRepository = productSpecRepository;

        this.productRepository = productRepository;
        this.productInnerCollectService = productInnerCollectService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void in(InStockDTO dto) {
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
        } else {
            warehouseProduct.setAmount(warehouseProduct.getAmount() + totalAmount.orElse(0));

        }
        warehouseProduct.setWarehouseId(dto.getWarehouseId());
        warehouseProduct.setLastTime(LocalDateTime.now());
        warehouseProduct.setProductId(dto.getProductId());
        warehouseProductRepository.save(warehouseProduct);
        // todo 减去消耗

        // todo 异步记录过程
    }


    @Override
    @Transactional(rollbackFor =Exception.class)
    public void out(OutStockDTO out) {
        // 减少规格产品的数量
        out.getSpecStocks().forEach(specStock -> {
            WarehouseProductSpec warehouseProductSpec =
                    warehouseProductSpecRepository.getByAllId(out.getWarehouseId(), out.getProductId(), specStock.getProductSpecId());
            Integer left = warehouseProductSpec.getAmount() - specStock.getAmount();
            if (left < 0) {
                throw new ValidationException("outofstock");
            }
            warehouseProductSpec.setAmount(left);

            warehouseProductSpecRepository.save(warehouseProductSpec);
        });
        // 减少总数量
        Optional<Integer> totalAmount = out.getSpecStocks().stream().map(ProductSpecStock::getAmount).reduce((a, b) -> a + b);
        WarehouseProduct warehouseProduct = warehouseProductRepository
                .getByWarehouseIdAndProductId(out.getWarehouseId(), out.getProductId());
        warehouseProduct.setAmount(warehouseProduct.getAmount() - totalAmount.orElse(0));
        warehouseProductRepository.save(warehouseProduct);
        // todo 异步记录过程

    }


    public StockVO getVO(Integer warehouseId, Integer productId) {

        StockVO stockVO = warehouseProductRepository.getStock(warehouseId, productId);
        List<WarehouseProductSpec> specs = warehouseProductSpecRepository.findByWarehouseIdAndProductId(warehouseId, productId);
        stockVO.setSpecs(Converter.of(specs).convert(WarehouseProductSpecVO::from));
        return stockVO;
    }

    @Override
    public Page<StockVO> findByTeam(String teamId, StockQuery stockQuery, Pageable pageable) {
        val voPage = warehouseProductRepository.findByTeam(teamId, stockQuery, pageable);
        val productIds = voPage.getContent().stream().map(StockVO::getProductId).collect(Collectors.toList());
        val voMap = productRepository.findByIds(productIds);
        return voPage.map(vo -> {
            // todo cache
            val specs = warehouseProductSpecRepository.findByWarehouseIdAndProductId(vo.getWarehouseId(), vo.getProductId());
            val product = voMap.get(vo.getProductId());
            vo.setProductName(product.getName());
            vo.setProductImage(product.getImage());
            vo.setSpecs(Converter.of(specs).convert(WarehouseProductSpecVO::from));
            return vo;
        });
    }

    @Override
    public List<WarehouseProductSpec> getByWarehouseId(Integer warehouseId) {
        return warehouseProductSpecRepository.findByWarehouseId(warehouseId);
    }

    @Override
    public Page<StockBO> find(StockQuery stockQuery) {
        return null;
    }



}
