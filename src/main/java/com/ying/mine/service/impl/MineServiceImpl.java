package com.ying.mine.service.impl;

import com.ying.core.er.Loginer;
import com.ying.friend.service.FriendService;
import com.ying.friend.service.impl.FriendVO;
import com.ying.mine.service.MineService;
import com.ying.mine.vo.WarehouseVO;
import com.ying.product.model.Product;
import com.ying.product.model.Warehouse;
import com.ying.product.query.StockQuery;
import com.ying.product.service.ProductService;
import com.ying.product.service.StockService;
import com.ying.product.service.WarehouseService;
import com.ying.product.vo.ProductVO;
import com.ying.product.vo.StockVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2018/12/10
 */
@Service
public class MineServiceImpl implements MineService {

    private final WarehouseService warehouseService;
    private final StockService stockService;
    private final FriendService friendService;
    private final ProductService productService;

    public MineServiceImpl(WarehouseService warehouseService, StockService stockService, FriendService friendService, ProductService productService) {
        this.warehouseService = warehouseService;
        this.stockService = stockService;
        this.friendService = friendService;
        this.productService = productService;
    }

    @Override
    public List<WarehouseVO> listWarehouse() {
        List<Warehouse> warehouses = warehouseService.listByCompany(Loginer.companyId());
        return warehouses.stream().map(warehouse -> {
            WarehouseVO vo = new WarehouseVO();
            vo.setId(warehouse.getId());
            vo.setName(warehouse.getName());
            vo.setType(warehouse.getType());
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public Page<StockVO> findStock(StockQuery stockQuery, Pageable pageable) {
        return stockService.findByCompany(stockQuery, pageable);
    }

    @Override
    public List<FriendVO> listFriends() {
        return friendService.listByFromId(Loginer.userId());
    }

    @Override
    public List<ProductVO> listProduct() {
        return productService.listByCompany(Loginer.companyId());
    }

}
