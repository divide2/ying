package com.ying.order.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.data.del.SingleId;
import com.ying.core.data.properties.OrderStatusProperties;
import com.ying.core.er.Loginer;
import com.ying.core.root.query.QueryManager;
import com.ying.order.dto.OrderDTO;
import com.ying.order.dto.OrderDeliverDTO;
import com.ying.order.dto.OrderReceiveDTO;
import com.ying.order.dto.ProductSpecPrice;
import com.ying.order.model.Order;
import com.ying.order.model.OrderProduct;
import com.ying.order.model.OrderProductSpec;
import com.ying.order.model.QOrder;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.repo.OrderProductRepository;
import com.ying.order.repo.OrderProductSpecRepository;
import com.ying.order.repo.OrderRepository;
import com.ying.order.service.OrderConnectService;
import com.ying.order.service.OrderInnerConnectService;
import com.ying.order.service.OrderService;
import com.ying.order.vo.OrderVO;
import com.ying.product.dto.InStockDTO;
import com.ying.product.dto.OutStockDTO;
import com.ying.product.dto.ProductSpecStock;
import com.ying.product.model.ProductSpec;
import com.ying.product.model.WarehouseProductSpec;
import com.ying.product.vo.ProductVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author bvvy
 * @date 2018/12/17
 */
@Service
public class OrderServiceImpl extends SimpleBasicServiceImpl<Order, Integer, OrderRepository> implements OrderService {
    private final OrderConnectService orderConnectService;
    private final OrderStatusProperties orderStatus;
    private final OrderProductRepository orderProductRepository;
    private final OrderProductSpecRepository orderProductSpecRepository;
    private final OrderInnerConnectService orderInnerConnectService;
    private final OrderRepository orderRepository;


    public OrderServiceImpl(OrderConnectService orderConnectService,
                            OrderStatusProperties orderStatus,
                            OrderProductRepository orderProductRepository,
                            OrderProductSpecRepository orderProductSpecRepository,
                            OrderInnerConnectService orderInnerConnectService, OrderRepository orderRepository) {
        this.orderConnectService = orderConnectService;

        this.orderStatus = orderStatus;
        this.orderProductRepository = orderProductRepository;
        this.orderProductSpecRepository = orderProductSpecRepository;
        this.orderInnerConnectService = orderInnerConnectService;
        this.orderRepository = orderRepository;
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
        // todo 这估计是要分离的 个人的操作都应该到mine 中去  这个参数该是传递进来的 暂时先这样 目前有点不清晰
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
                ProductSpec productSpec = orderConnectService.getProductSpec(spec.getProductSpecId());
                OrderProductSpec orderProductSpec = new OrderProductSpec();
                orderProductSpec.setOrderProductId(orderProduct.getId());
                orderProductSpec.setAmount(spec.getAmount());
                orderProductSpec.setSpecName(productSpec.getName());
                orderProductSpec.setProductSpecId(productSpec.getId());
                orderProductSpec.setOrderId(order.getId());
                orderProductSpec.setProductName(product.getName());
                orderProductSpec.setPrice(productSpec.getPrice());
                orderProductSpec.setProductId(productId);
                orderProductSpecRepository.save(orderProductSpec);
            });
        });
        // todo 是不是真的需要 这样做? 先这样多张表 相当于采购和销售记录
        orderInnerConnectService.addPurchaseOrder(order);
        orderConnectService.sendMessage(order);
    }

    /**
     * todo 状态模式
     * @param confirm confirm id
     */
    @Override
    public void confirm(SingleId confirm) {
        Order order = this.get(confirm.getId());
        order.setStatus(orderStatus.getWaitingDeliver());
        orderInnerConnectService.addSellOrder(order);
        this.update(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmDeliver(OrderDeliverDTO deliver) {
        Order order = this.get(deliver.getOrderId());
        //获取的是订单的商品规格数量
        List<OrderProductSpec> opses = orderProductSpecRepository.findByOrderId(deliver.getOrderId());
        Map<Integer, List<OrderProductSpec>> collect = opses.stream().collect(Collectors.groupingBy(OrderProductSpec::getProductId));

        // todo 调用出库 nice job
        collect.forEach((productId,specs)-> {
            OutStockDTO outStock = new OutStockDTO();
            outStock.setProductId(productId);
            outStock.setWarehouseId(deliver.getWarehouseId());
            List<ProductSpecStock> productSpecStocks = specs.stream()
                    .map(spec -> new ProductSpecStock(spec.getProductSpecId(), spec.getAmount())).collect(toList());
            outStock.setSpecStocks(productSpecStocks);
            orderConnectService.outStock(outStock);
        });
        order.setStatus(orderStatus.getWaitingReceive());
        this.update(order);
    }


    @Override
    public void confirmReceive(OrderReceiveDTO receive) {
        Order order = this.get(receive.getOrderId());
        List<OrderProductSpec> opses = orderProductSpecRepository.findByOrderId(receive.getOrderId());
        // todo 调用入库
        InStockDTO inStock = new InStockDTO();
        order.setStatus(orderStatus.getFinish());
        this.update(order);
    }

    @Override
    public Page<OrderVO> findUserReceiveOrder(Integer userId, OrderQueryParam queryParam, Pageable pageable) {
        BooleanExpression predicate = new QueryManager(queryParam).predicate();
        QOrder order = QOrder.order;
        predicate = predicate.and(order.toId.eq(userId));
        Page<Order> page = orderRepository.findAll(predicate, pageable);
        return page.map(OrderVO::from);
    }
}
