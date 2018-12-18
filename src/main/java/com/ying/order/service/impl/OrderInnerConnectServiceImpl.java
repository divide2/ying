package com.ying.order.service.impl;

import com.ying.order.dto.PurchaseOrderDTO;
import com.ying.order.model.Order;
import com.ying.order.service.OrderInnerConnectService;
import com.ying.order.service.PurchaseOrderService;
import com.ying.order.service.SellOrderService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/12/18
 */
@Service
public class OrderInnerConnectServiceImpl implements OrderInnerConnectService {

    private final PurchaseOrderService purchaseOrderService;
    private final SellOrderService sellOrderService;

    public OrderInnerConnectServiceImpl(PurchaseOrderService purchaseOrderService,
                                        SellOrderService sellOrderService) {
        this.purchaseOrderService = purchaseOrderService;
        this.sellOrderService = sellOrderService;
    }

    @Override
    public void addPurchaseOrder(Order order) {
        purchaseOrderService.add(new PurchaseOrderDTO(order.getId(),order.getFromId(),order.getToId()));
    }

    @Override
    public void addSellOrder(Order order) {
        sellOrderService.add(new PurchaseOrderDTO(order.getId(), order.getToId(), order.getFromId()));
    }
}
