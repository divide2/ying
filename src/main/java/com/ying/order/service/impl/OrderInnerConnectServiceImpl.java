package com.ying.order.service.impl;

import com.ying.order.model.Order;
import com.ying.order.service.OrderInnerConnectService;
import com.ying.order.service.PurchaseOrderService;
import com.ying.order.service.SellOrderService;
import org.springframework.stereotype.Service;

/**
 * todo remove?
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
    }

    @Override
    public void addSellOrder(Order order) {
    }
}
