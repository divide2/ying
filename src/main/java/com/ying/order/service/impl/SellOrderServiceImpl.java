package com.ying.order.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.order.dto.PurchaseOrderDTO;
import com.ying.order.model.SellOrder;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.repo.OrderRepository;
import com.ying.order.repo.SellOrderRepository;
import com.ying.order.service.OrderConnectService;
import com.ying.order.service.SellOrderService;
import com.ying.order.vo.OrderVO;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/12/18
 */
@Service
public class SellOrderServiceImpl extends SimpleBasicServiceImpl<SellOrder, Integer, SellOrderRepository> implements SellOrderService {

    private final OrderConnectService orderConnectService;
    private final SellOrderRepository sellOrderRepository;
    private final OrderRepository orderRepository;

    public SellOrderServiceImpl(OrderConnectService orderConnectService,
                                SellOrderRepository sellOrderRepository,
                                OrderRepository orderRepository) {
        this.orderConnectService = orderConnectService;
        this.sellOrderRepository = sellOrderRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void add(PurchaseOrderDTO dto) {
        val friend = orderConnectService.getOnlyFriend(dto.getFromId(), dto.getToId());
        SellOrder sellOrder = new SellOrder();
        sellOrder.setOrderId(dto.getOrderId());
        sellOrder.setToId(dto.getToId());
        sellOrder.setToName(friend.getMemoName());
        sellOrder.setFromId(dto.getFromId());
        // todo 是不是需要 先不要
        // sellOrder.setFromName(friend.getMemoName());
        this.add(sellOrder);
    }

    @Override
    public Page<OrderVO> findByUser(Integer userId, OrderQueryParam query, Pageable pageable) {
        return orderRepository.findSellOrderByUser(userId, query, pageable);
    }

}
