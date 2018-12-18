package com.ying.order.service.impl;

import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.data.properties.OrderStatusProperties;
import com.ying.core.er.Loginer;
import com.ying.friend.vo.FriendVO;
import com.ying.order.dto.OrderConfirmDTO;
import com.ying.order.dto.OrderDTO;
import com.ying.order.dto.ProductSpecPrice;
import com.ying.order.model.Order;
import com.ying.order.model.OrderProduct;
import com.ying.order.model.OrderProductSpec;
import com.ying.order.model.SellOrder;
import com.ying.order.repo.OrderProductRepository;
import com.ying.order.repo.OrderRepository;
import com.ying.order.repo.SellOrderRepository;
import com.ying.order.service.OrderConnectService;
import com.ying.order.service.OrderService;
import com.ying.order.service.PurchaseOrderService;
import com.ying.product.vo.ProductVO;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2018/12/17
 */
@Service
public class OrderServiceImpl extends SimpleBasicServiceImpl<Order, Integer, OrderRepository> implements OrderService {
    private final OrderConnectService orderConnectService;
    private final OrderStatusProperties orderStatus;
    private final OrderProductRepository orderProductRepository;
    private final SellOrderRepository sellOrderRepository;


    public OrderServiceImpl(OrderConnectService orderConnectService,
                            OrderStatusProperties orderStatus,
                            OrderProductRepository orderProductRepository,
                            SellOrderRepository sellOrderRepository) {
        this.orderConnectService = orderConnectService;

        this.orderStatus = orderStatus;
        this.orderProductRepository = orderProductRepository;
        this.sellOrderRepository = sellOrderRepository;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(OrderDTO dto) {
        UserVO user = orderConnectService.getUser(dto.getToId());
        Order order = new Order();
        order.setAttachment(dto.getAttachment());
        order.setBalancePayment(dto.getBalancePayment());
        order.setCreateTime(LocalDateTime.now());
        order.setDeliveryDate(dto.getDeliveryDate());
        order.setEarnestMoney(dto.getEarnestMoney());
        order.setRemarks(dto.getRemarks());
        order.setFromId(Loginer.userId());
        order.setFromName(Loginer.username());
        order.setToId(dto.getToId());
        order.setToName(user.getUsername());
        order.setStatus(orderStatus.getWaitingConfirm());
        List<ProductSpecPrice> productSpecPrices = dto.getProductSpecPrices();
        Map<Integer, List<ProductSpecPrice>> productSpecMap = productSpecPrices.stream().collect(
                Collectors.groupingBy(ProductSpecPrice::getProductId)
        );
        this.add(order);
        // todo 分开？
        productSpecMap.forEach((productId, productSpecPriceList) -> {
            ProductVO product = orderConnectService.getProductById(productId);
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(order.getId());
            orderProduct.setProductId(productId);
            orderProduct.setProductName(product.getName());
            orderProductRepository.save(orderProduct);
            productSpecPriceList.forEach(spec -> {
                OrderProductSpec orderProductSpec = new OrderProductSpec();
                orderProductSpec.setOrderProductId(orderProduct.getId());
                orderProductSpec.setAmount(spec.getAmount());
                orderProductSpec.setSpecName(spec.getSpecName());
                orderProductSpec.setOrderId(order.getId());
                orderProduct.setProductId(productId);
            });
        });
        // todo 是不是真的需要 这样做? 先这样多张表 相当于采购和销售记录
        orderConnectService.addPurchaseOrder(order);
        orderConnectService.sendMessage(order);
    }

    @Override
    public void confirm(OrderConfirmDTO confirm) {
        Order order = this.get(confirm.getOrderId());
        order.setStatus(orderStatus.getWaitingDeliver());
        orderConnectService.addSellOrder(order);
        this.update(order);
    }

}
