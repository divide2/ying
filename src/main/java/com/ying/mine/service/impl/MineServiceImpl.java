package com.ying.mine.service.impl;

import com.ying.core.er.Loginer;
import com.ying.friend.service.FriendService;
import com.ying.friend.vo.FriendVO;
import com.ying.mine.service.MineService;
import com.ying.mine.vo.WarehouseVO;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.service.OrderService;
import com.ying.order.service.PurchaseOrderService;
import com.ying.order.service.SellOrderService;
import com.ying.order.vo.OrderVO;
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
    private final PurchaseOrderService purchaseOrderService;
    private final SellOrderService sellOrderService;
    private final OrderService orderService;

    public MineServiceImpl(WarehouseService warehouseService,
                           StockService stockService,
                           FriendService friendService,
                           ProductService productService,
                           PurchaseOrderService purchaseOrderService,
                           SellOrderService sellOrderService, OrderService orderService) {
        this.warehouseService = warehouseService;
        this.stockService = stockService;
        this.friendService = friendService;
        this.productService = productService;
        this.purchaseOrderService = purchaseOrderService;
        this.sellOrderService = sellOrderService;
        this.orderService = orderService;
    }

    @Override
    public Page<OrderVO> findReceiveOrder(OrderQueryParam queryParam, Pageable pageable) {
        return orderService.findUserReceiveOrder(Loginer.userId(), queryParam, pageable);
    }

    @Override
    public Page<OrderVO> findPurchaseOrder(OrderQueryParam query, Pageable pageable) {
        return purchaseOrderService.findByUser(Loginer.userId(), query, pageable);
    }

    @Override
    public Page<OrderVO> findSellOrder(OrderQueryParam query, Pageable pageable) {
        return sellOrderService.findByUser(Loginer.userId(), query, pageable);
    }

    @Override
    public Page<OrderVO> findSendOrder(OrderQueryParam queryParam, Pageable pageable) {
        return orderService.findUserSendOrder(Loginer.userId(), queryParam, pageable);
    }

    @Override
    public List<WarehouseVO> listWarehouse() {
        List<Warehouse> warehouses = warehouseService.listByUser(Loginer.userId());
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
        return stockService.findByUser(stockQuery, pageable);
    }

    @Override
    public List<FriendVO> listFriends() {
        return friendService.listByFromId(Loginer.userId());
    }

    @Override
    public Page<ProductVO> findProduct(Pageable pageable) {
        return productService.findByUser(Loginer.userId(), pageable);
    }

    @Override
    public Page<ProductVO> findCompanyProduct(Pageable pageable) {
        return productService.findByCompany(Loginer.companyId(), pageable);
    }
}
