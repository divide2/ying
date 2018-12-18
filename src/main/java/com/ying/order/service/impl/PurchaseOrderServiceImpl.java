package com.ying.order.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.order.model.Order;
import com.ying.order.model.PurchaseOrder;
import com.ying.order.query.PurchaseOrderQuery;
import com.ying.order.repo.OrderRepository;
import com.ying.order.repo.PurchaseOrderRepository;
import com.ying.order.service.PurchaseOrderService;
import com.ying.order.vo.PurchaseOrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Service
public class PurchaseOrderServiceImpl extends SimpleBasicServiceImpl<PurchaseOrder, Integer, PurchaseOrderRepository> implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final OrderRepository orderRepository;


    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository,
                                    OrderRepository orderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void add(Order order) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderId(order.getId());
        purchaseOrder.setToId(order.getToId());
        purchaseOrder.setToName(order.getToName());
        purchaseOrder.setFromId(order.getFromId());
        purchaseOrder.setFromName(order.getFromName());
        this.add(purchaseOrder);
    }

    @Override
    public Page<PurchaseOrderVO> findByUser(Integer userId, PurchaseOrderQuery query, Pageable pageable) {
        Page<PurchaseOrder> page = purchaseOrderRepository.findAll(pageable);
        return page.map(item -> {
            Order order = orderRepository.getOne(item.getOrderId());
            return new PurchaseOrderVO();
        });
    }
}
