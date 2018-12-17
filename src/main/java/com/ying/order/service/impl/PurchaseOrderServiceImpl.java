package com.ying.order.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.er.Loginer;
import com.ying.friend.model.Friend;
import com.ying.friend.service.impl.FriendVO;
import com.ying.order.dto.PurchaseOrderDTO;
import com.ying.order.model.Order;
import com.ying.order.model.PurchaseOrder;
import com.ying.order.repo.OrderRepository;
import com.ying.order.repo.PurchaseOrderRepository;
import com.ying.order.service.OrderConnectService;
import com.ying.order.service.PurchaseOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Service
public class PurchaseOrderServiceImpl extends SimpleBasicServiceImpl<PurchaseOrder,Integer, PurchaseOrderRepository> implements PurchaseOrderService {
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
    @Transactional(rollbackFor = Exception.class)
    public void add(PurchaseOrderDTO dto) {

        //todo 弄反了
        // 第一步
        FriendVO friend = orderConnectService.getOnlyFriend(Loginer.userId(), dto.getToId());
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setAttachment(dto.getAttachment());
        purchaseOrder.setBalancePayment(dto.getBalancePayment());
        purchaseOrder.setCreateTime(LocalDateTime.now());
        purchaseOrder.setDeliveryDate(dto.getDeliveryDate());
        purchaseOrder.setEarnestMoney(dto.getEarnestMoney());
        purchaseOrder.setRemarks(dto.getRemarks());
        purchaseOrder.setFromId(Loginer.userId());
        purchaseOrder.setFromName(Loginer.username());
        purchaseOrder.setToId(dto.getToId());
        purchaseOrder.setToName(friend.getMemoName());
        this.add(purchaseOrder);
        //发送消息
        this.sendOrderMessage(purchaseOrder);
        //第二步
        this.saveOrder(purchaseOrder);

    }

    private void saveOrder(PurchaseOrder purchaseOrder) {
        Order order = new Order();
        FriendVO friend = orderConnectService.getOnlyFriend(purchaseOrder.getToId(), purchaseOrder.getFromId());
        order.setFromId(purchaseOrder.getFromId());
        order.setFromName(friend.getMemoName());
        order.setToId(purchaseOrder.getToId());
        order.setToName(purchaseOrder.getToName());
        order.setPurchaseOrderId(purchaseOrder.getId());

    }

    private void sendOrderMessage(PurchaseOrder purchaseOrder) {
        //todo 发送消息

    }
}
