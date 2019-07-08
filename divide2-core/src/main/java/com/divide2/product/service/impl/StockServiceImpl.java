package com.divide2.product.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.divide2.core.exception.ValidationException;
import com.divide2.core.root.converter.Converter;
import com.divide2.product.bo.StockBO;
import com.divide2.product.dto.InStockDTO;
import com.divide2.product.dto.OutStockDTO;
import com.divide2.product.dto.ProductSpecStock;
import com.divide2.product.model.*;
import com.divide2.product.query.StockQuery;
import com.divide2.product.repo.*;
import com.divide2.product.service.ProductConnectService;
import com.divide2.product.service.StockService;
import com.divide2.product.vo.StockStreamVO;
import com.divide2.product.vo.StockVO;
import com.divide2.product.vo.WarehouseProductSpecVO;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
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

    private final StockRepository stockRepository;
    private final SpecStockRepository specStockRepository;
    private final ProductSpecRepository productSpecRepository;
    private final ProductRepository productRepository;
    private final StockStreamRepository stockStreamRepository;
    private final ProductConnectService productConnectService;

    public StockServiceImpl(StockRepository stockRepository,
                            SpecStockRepository specStockRepository,
                            ProductSpecRepository productSpecRepository,
                            ProductRepository productRepository,
                            StockStreamRepository stockStreamRepository,
                            ProductConnectService productConnectService) {
        this.stockRepository = stockRepository;
        this.specStockRepository = specStockRepository;
        this.productSpecRepository = productSpecRepository;

        this.productRepository = productRepository;
        this.stockStreamRepository = stockStreamRepository;
        this.productConnectService = productConnectService;
    }

    @Override
    public Page<StockStreamVO> findStockStream(String teamId, StockQuery stockQuery, Pageable pageable) {

        QStockStream stockStream = QStockStream.stockStream;
        BooleanExpression predicate = stockStream.teamId.eq(teamId);
        if (StringUtils.isNotBlank(stockQuery.getWarehouseId())) {
            predicate.and(stockStream.warehouseId.eq(stockQuery.getWarehouseId()));
        }
        return stockStreamRepository.findAll(predicate, pageable).map(item -> {
            Product product = productConnectService.getProduct(item.getProductId());
            ProductSpec spec = productConnectService.getProductSpec(item.getProductSpecId());
            return new StockStreamVO(
                    item.getTeamId(),
                    item.getWarehouseId(),
                    item.getProductId(),
                    item.getProductSpecId(),
                    product.getName(),
                    spec.getName(),
                    item.getType(),
                    item.getStream(),
                    item.getAmount(),
                    item.getCreateTime()
            );
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void in(InStockDTO dto) {
        // 先存入规格的库存
        dto.getSpecStocks().forEach(tSpecStock -> {
            SpecStock specStock = specStockRepository.getByAllId(
                    dto.getWarehouseId(), dto.getProductId(), tSpecStock.getProductSpecId());
            if (specStock == null) {
                specStock = new SpecStock();
                specStock.setAmount(tSpecStock.getAmount());
            } else {
                specStock.setAmount(specStock.getAmount() + tSpecStock.getAmount());
            }
            ProductSpec productSpec = productSpecRepository.getOne(tSpecStock.getProductSpecId());
            specStock.setWarehouseId(dto.getWarehouseId());
            specStock.setProductId(dto.getProductId());
            specStock.setProductSpecId(tSpecStock.getProductSpecId());
            specStock.setProductSpecName(productSpec.getName());
            specStock.setTeamId(dto.getTeamId());
            specStockRepository.save(specStock);
            //存入出入库流水信息

            StockStream stream = new StockStream();
            stream.setWarehouseId(dto.getWarehouseId());
            stream.setProductId(dto.getProductId());
            stream.setCreateTime(LocalDateTime.now());
            stream.setAmount(specStock.getAmount());
            stream.setStream(tSpecStock.getAmount());
            stream.setType(dto.getType());
            stream.setTeamId(dto.getTeamId());
            stream.setProductSpecId(tSpecStock.getProductSpecId());
            stockStreamRepository.save(stream);

        });
        // 再存入单个商品的库存
        Optional<Integer> totalAmount = dto.getSpecStocks().stream().map(ProductSpecStock::getAmount).reduce((a, b) -> a + b);
        Stock stock = stockRepository
                .getByWarehouseIdAndProductId(dto.getWarehouseId(), dto.getProductId());
        if (stock == null) {
            stock = new Stock();
            stock.setAmount(totalAmount.orElse(0));
        } else {
            stock.setAmount(stock.getAmount() + totalAmount.orElse(0));
        }
        stock.setTeamId(dto.getTeamId());
        stock.setWarehouseId(dto.getWarehouseId());
        stock.setLastTime(LocalDateTime.now());
        stock.setProductId(dto.getProductId());
        stockRepository.save(stock);
        // todo 减去消耗

    }


    @Override
    @Transactional(rollbackFor =Exception.class)
    public void out(OutStockDTO out) {
        // 减少规格产品的数量
        out.getSpecStocks().forEach(tSpecStock -> {
            SpecStock specStock =
                    specStockRepository.getByAllId(out.getWarehouseId(), out.getProductId(), tSpecStock.getProductSpecId());
            if (specStock == null) {
                throw new ValidationException("no_stock");
            }
            Integer left = specStock.getAmount() - tSpecStock.getAmount();
            if (left < 0) {
                throw new ValidationException("outofstock");
            }
            specStock.setAmount(left);
            specStockRepository.save(specStock);
            //记录出入库流水
            StockStream stream = new StockStream();
            stream.setWarehouseId(out.getWarehouseId());
            stream.setProductId(out.getProductId());
            // 出库数量为负数
            stream.setStream(-tSpecStock.getAmount());
            stream.setType(out.getType());
            stream.setTeamId(out.getTeamId());
            stream.setProductSpecId(tSpecStock.getProductSpecId());
            stream.setAmount(left);
            stream.setCreateTime(LocalDateTime.now());
            stockStreamRepository.save(stream);
        });
        // 减少总数量
        Optional<Integer> totalAmount = out.getSpecStocks().stream().map(ProductSpecStock::getAmount).reduce((a, b) -> a + b);
        Stock stock = stockRepository
                .getByWarehouseIdAndProductId(out.getWarehouseId(), out.getProductId());
        stock.setAmount(stock.getAmount() - totalAmount.orElse(0));
        stockRepository.save(stock);


    }


    public StockVO getVO(String warehouseId, String productId) {

        StockVO stockVO = stockRepository.getStock(warehouseId, productId);
        List<SpecStock> specs = specStockRepository.findByWarehouseIdAndProductId(warehouseId, productId);
        stockVO.setSpecs(Converter.of(specs).convert(WarehouseProductSpecVO::from));
        return stockVO;
    }

    @Override
    public Page<StockVO> findByTeam(String teamId, StockQuery stockQuery, Pageable pageable) {
        val voPage = stockRepository.findByTeam(teamId, stockQuery, pageable);
        val productIds = voPage.getContent().stream().map(StockVO::getId).collect(Collectors.toList());
        val voMap = productRepository.findByIds(productIds);
        return voPage.map(vo -> {
            // todo cache
            val specs = specStockRepository.findByProductId(vo.getId());
            val product = voMap.get(vo.getId());
            vo.setName(product.getName());
            vo.setImage(product.getImage());
            vo.setSpecs(Converter.of(specs).convert(WarehouseProductSpecVO::from));
            return vo;
        });
    }

    @Override
    public List<SpecStock> getByWarehouseId(String warehouseId) {
        return specStockRepository.findByWarehouseId(warehouseId);
    }

    @Override
    public Page<StockBO> find(StockQuery stockQuery) {
        return null;
    }



}
