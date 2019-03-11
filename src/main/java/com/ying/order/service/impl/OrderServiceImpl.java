package com.ying.order.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.data.del.SingleStringId;
import com.ying.core.data.properties.OrderStatusProperties;
import com.ying.core.data.properties.StockTypeProperties;
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
import com.ying.order.service.OrderService;
import com.ying.order.vo.OrderVO;
import com.ying.product.dto.InStockDTO;
import com.ying.product.dto.OutStockDTO;
import com.ying.product.dto.ProductSpecStock;
import com.ying.product.model.ProductSpec;
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
public class OrderServiceImpl extends SimpleBasicServiceImpl<Order, String, OrderRepository> implements OrderService {
    private final OrderConnectService orderConnectService;
    private final OrderStatusProperties orderStatus;
    private final StockTypeProperties stockType;
    private final OrderProductRepository orderProductRepository;
    private final OrderProductSpecRepository orderProductSpecRepository;
    private final OrderRepository orderRepository;


    public OrderServiceImpl(OrderConnectService orderConnectService,
                            OrderStatusProperties orderStatus,
                            StockTypeProperties stockType, OrderProductRepository orderProductRepository,
                            OrderProductSpecRepository orderProductSpecRepository,
                            OrderRepository orderRepository) {
        this.orderConnectService = orderConnectService;

        this.orderStatus = orderStatus;
        this.stockType = stockType;
        this.orderProductRepository = orderProductRepository;
        this.orderProductSpecRepository = orderProductSpecRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(OrderDTO dto) {
        Order order = new Order();
        order.setToTeamId(dto.getToTeamId());
        order.setAttachment(dto.getAttachment());
        order.setBalancePayment(dto.getBalancePayment());
        order.setCreateTime(LocalDateTime.now());
        order.setDeliveryDate(dto.getDeliveryDate());
        order.setEarnestMoney(dto.getEarnestMoney());
        order.setRemarks(dto.getRemarks());
        order.setFromId(Loginer.userId());
        order.setFromName(Loginer.username());

        order.setStatus(orderStatus.getWaitingConfirm());
        List<ProductSpecPrice> productSpecPrices = dto.getProductSpecPrices();
        Map<String, List<ProductSpecPrice>> productSpecMap = productSpecPrices.stream().collect(
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
                orderProductSpec.setUnit(spec.getUnit());
                orderProductSpec.setSpecName(productSpec.getName());
                orderProductSpec.setProductSpecId(productSpec.getId());
                orderProductSpec.setOrderId(order.getId());
                orderProductSpec.setProductName(product.getName());
                orderProductSpec.setPrice(productSpec.getPrice());
                orderProductSpec.setProductId(productId);
                orderProductSpecRepository.save(orderProductSpec);
            });
        });
        orderConnectService.sendMessage(order);
    }

    /**
     * todo 状态模式
     *
     * @param confirm confirm id
     */
    @Override
    public void confirm(SingleStringId confirm) {
        Order order = this.get(confirm.getId());
        order.setStatus(orderStatus.getWaitingDeliver());
        this.update(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmDeliver(OrderDeliverDTO deliver) {
        Order order = this.get(deliver.getOrderId());
        //获取的是订单的商品规格数量
        List<OrderProductSpec> opses = orderProductSpecRepository.findByOrderId(deliver.getOrderId());
        Map<String, List<OrderProductSpec>> collect = opses.stream().collect(Collectors.groupingBy(OrderProductSpec::getProductId));
        // 调用出库 nice job
        collect.forEach((productId, specs) -> {
            OutStockDTO outStock = new OutStockDTO();
            outStock.setProductId(productId);
            outStock.setWarehouseId(deliver.getWarehouseId());
            List<ProductSpecStock> productSpecStocks = specs.stream()
                    .map(spec -> new ProductSpecStock(spec.getProductSpecId(), spec.getAmount())).collect(toList());
            outStock.setSpecStocks(productSpecStocks);
            outStock.setTeamId(deliver.getTeamId());
            outStock.setType(stockType.getDeliver());
            orderConnectService.outStock(outStock);
        });
        order.setStatus(orderStatus.getWaitingReceive());
        this.update(order);
    }


    @Override
    public void confirmReceive(OrderReceiveDTO receive) {
        Order order = this.get(receive.getOrderId());
        List<OrderProductSpec> opses = orderProductSpecRepository.findByOrderId(receive.getOrderId());
        Map<String, List<OrderProductSpec>> collect = opses.stream().collect(Collectors.groupingBy(OrderProductSpec::getProductId));
        // 调用入库 nice job
        collect.forEach((productId, specs) -> {
            InStockDTO inStock = new InStockDTO();
            inStock.setProductId(productId);
            inStock.setWarehouseId(receive.getWarehouseId());
            List<ProductSpecStock> productSpecStocks = specs.stream()
                    .map(spec -> new ProductSpecStock(spec.getProductSpecId(), spec.getAmount())).collect(toList());
            inStock.setSpecStocks(productSpecStocks);
            inStock.setTeamId(receive.getTeamId());
            inStock.setType(stockType.getReceive());
            orderConnectService.inStock(inStock);
        });
        order.setStatus(orderStatus.getFinish());
        this.update(order);
    }

    // todo
    @Override
    public Page<OrderVO> findTeamReceiveOrder(String teamId, OrderQueryParam queryParam, Pageable pageable) {
        BooleanExpression predicate = QueryManager.resolvePredicate(queryParam);
        QOrder order = QOrder.order;
        predicate = predicate.and(order.toTeamId.eq(teamId));
        Page<Order> page = orderRepository.findAll(predicate, pageable);
        return page.map(OrderVO::from);
    }

    @Override
    public Page<OrderVO> findTeamSendOrder(String teamId, OrderQueryParam queryParam, Pageable pageable) {
        BooleanExpression predicate = QueryManager.resolvePredicate(queryParam);
        QOrder order = QOrder.order;
        predicate = predicate.and(order.fromTeamId.eq(teamId));
        Page<Order> page = orderRepository.findAll(predicate, pageable);
        return page.map(OrderVO::from);
    }

}
