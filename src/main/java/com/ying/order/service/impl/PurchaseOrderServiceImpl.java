package com.ying.order.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.order.dto.PurchaseOrderDTO;
import com.ying.order.model.PurchaseOrder;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.repo.OrderRepository;
import com.ying.order.repo.PurchaseOrderRepository;
import com.ying.order.service.OrderConnectService;
import com.ying.order.service.PurchaseOrderService;
import com.ying.order.vo.OrderVO;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Service
public class PurchaseOrderServiceImpl extends SimpleBasicServiceImpl<PurchaseOrder, String, PurchaseOrderRepository> implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final OrderRepository orderRepository;
    private final OrderConnectService orderConnectService;


    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository,
                                    OrderRepository orderRepository,
                                    OrderConnectService orderConnectService) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.orderRepository = orderRepository;
        this.orderConnectService = orderConnectService;
    }

    @Override
    public void add(PurchaseOrderDTO dto) {
        val friend = orderConnectService.getOnlyFriend(dto.getFromId(), dto.getToId());
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderId(dto.getOrderId());
        purchaseOrder.setToId(dto.getToId());
        purchaseOrder.setToName(friend.getMemoName());
        purchaseOrder.setFromId(dto.getFromId());
        // todo 是不是需要 先不要
//        purchaseOrder.setFromName(fromUser.getUsername());
        this.add(purchaseOrder);
    }

    @Override
    public Page<OrderVO> findByUser(Integer userId, OrderQueryParam query, Pageable pageable) {
        return orderRepository.findPurchaseOrderByUser(userId, query, pageable);
    }
}
