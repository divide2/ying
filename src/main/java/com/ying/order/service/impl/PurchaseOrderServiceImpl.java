package com.ying.order.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.er.Loginer;
import com.ying.friend.model.Friend;
import com.ying.friend.repo.FriendRepository;
import com.ying.order.dto.PurchaseOrderDTO;
import com.ying.order.model.PurchaseOrder;
import com.ying.order.repo.PurchaseOrderRepository;
import com.ying.order.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Service
public class PurchaseOrderServiceImpl extends SimpleBasicServiceImpl<PurchaseOrder,Integer, PurchaseOrderRepository> implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final FriendRepository friendRepository;


    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, FriendRepository friendRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.friendRepository = friendRepository;
    }

    @Override
    public void add(PurchaseOrderDTO dto) {
        /**
         * 第一步 添加一个采购单 采购商品和规格数量 为未确认状态 消息通知
         * 第二步 发送一个订单给好友
         * 第三步 好友确认订单后把 采购单变为已确认
         * 第四部 好友然后增加一条销售单 为未出库
         * 第五步 点击出库 选择商品 选择消耗品
         * 第六步 采购单变成已发货
         * 第七步 确认收货 支付尾款
         */
        // 第一步
        Friend friend = friendRepository.findMyOnlyFriend(Loginer.userId(), dto.getToId());
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
        sendOrderMessage(purchaseOrder);

        //第二部
    }

    private void sendOrderMessage(PurchaseOrder purchaseOrder) {


    }
}
