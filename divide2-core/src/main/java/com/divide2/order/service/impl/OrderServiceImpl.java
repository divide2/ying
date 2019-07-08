package com.divide2.order.service.impl;

import com.divide2.product.unit.Uniter;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.divide2.core.basic.service.impl.SimpleBasicServiceImpl;
import com.divide2.core.data.del.SingleStringId;
import com.divide2.core.data.properties.OrderStatusProperties;
import com.divide2.core.data.properties.StockTypeProperties;
import com.divide2.core.er.Loginer;
import com.divide2.core.root.query.QueryManager;
import com.divide2.order.dto.OrderDTO;
import com.divide2.order.dto.OrderDeliverDTO;
import com.divide2.order.dto.OrderReceiveDTO;
import com.divide2.order.dto.ProductSpecPrice;
import com.divide2.order.model.Order;
import com.divide2.order.model.OrderProduct;
import com.divide2.order.model.OrderProductSpec;
import com.divide2.order.model.QOrder;
import com.divide2.order.query.OrderQueryParam;
import com.divide2.order.repo.OrderProductRepository;
import com.divide2.order.repo.OrderProductSpecRepository;
import com.divide2.order.repo.OrderRepository;
import com.divide2.order.service.OrderConnectService;
import com.divide2.order.service.OrderService;
import com.divide2.order.vo.OrderVO;
import com.divide2.product.dto.InStockDTO;
import com.divide2.product.dto.OutStockDTO;
import com.divide2.product.dto.ProductSpecStock;
import com.divide2.product.model.ProductSpec;
import com.divide2.product.vo.ProductVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
        order.setFromTeamId(dto.getFromTeamId());
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
        BigDecimal totalPrice = BigDecimal.ZERO;
        // 增加订单产品和订单产品规格
        for (Map.Entry<String, List<ProductSpecPrice>> entry : productSpecMap.entrySet()) {
            String productId = entry.getKey();
            List<ProductSpecPrice> productSpecPriceList = entry.getValue();
            ProductVO product = orderConnectService.getProductById(productId);
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(order.getId());
            orderProduct.setProductId(productId);
            orderProduct.setProductName(product.getName());
            orderProductRepository.save(orderProduct);
            for (ProductSpecPrice spec : productSpecPriceList) {
                ProductSpec productSpec = orderConnectService.getProductSpec(spec.getProductSpecId());
                OrderProductSpec orderProductSpec = new OrderProductSpec();
                orderProductSpec.setOrderProductId(orderProduct.getId());
//                for (StockUnitDTO unit : spec.getUnits()) {
//
//                }
                orderProductSpec.setSpecName(productSpec.getName());
                orderProductSpec.setProductSpecId(productSpec.getId());
                orderProductSpec.setOrderId(order.getId());
                orderProductSpec.setProductName(product.getName());
                orderProductSpec.setPrice(productSpec.getPrice());
                orderProductSpec.setProductId(productId);
                totalPrice = totalPrice.add(productSpec.getPrice());
                orderProductSpecRepository.save(orderProductSpec);
            }
        }
        order.setTotalPrice(totalPrice);
        this.update(order);
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
        //获取的是订单的商Pro品规格数量
        List<OrderProductSpec> opses = orderProductSpecRepository.findByOrderId(deliver.getOrderId());
        Map<String, List<OrderProductSpec>> collect = opses.stream().collect(Collectors.groupingBy(OrderProductSpec::getProductId));
        // 调用出库 nice job
        collect.forEach((productId, specs) -> {
            OutStockDTO outStock = new OutStockDTO();
            outStock.setProductId(productId);
            outStock.setWarehouseId(deliver.getWarehouseId());
            List<ProductSpecStock> productSpecStocks = specs.stream()
                    .map(spec -> new ProductSpecStock(spec.getProductSpecId(), Uniter.compose(spec.getUnitAmounts()).getTotalAmount())).collect(toList());
            outStock.setSpecStocks(productSpecStocks);
            outStock.setTeamId(order.getToTeamId());
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
                    .map(spec -> new ProductSpecStock(spec.getProductSpecId(), Uniter.compose(spec.getUnitAmounts()).getTotalAmount())).collect(toList());
            inStock.setSpecStocks(productSpecStocks);
            inStock.setTeamId(order.getFromTeamId());
            inStock.setType(stockType.getReceive());
            orderConnectService.inStock(inStock);
        });
        order.setStatus(orderStatus.getFinish());
        this.update(order);
    }

    @Override
    public Page<OrderVO> findTeamReceiveOrder(String teamId, OrderQueryParam queryParam, Pageable pageable) {
        BooleanExpression predicate = QueryManager.resolvePredicate(queryParam);
        QOrder order = QOrder.order;
        predicate = predicate.and(order.toTeamId.eq(teamId));
        Page<Order> page = orderRepository.findAll(predicate, pageable);
        return page.map(this::toVO);
    }

    private OrderVO toVO(Order order) {
        return new OrderVO(
                order.getId(),
                order.getId(),
                order.getFromId(),
                order.getFromName(),
                order.getFromTeamId(),
                order.getToTeamId(),
                order.getOrderNo(),
                order.getEarnestMoney(),
                order.getBalancePayment(),
                order.getCreateTime(),
                order.getDeliveryDate(),
                order.getRemarks(),
                order.getAttachment(),
                order.getStatus(),
                order.getTotalPrice()
        );

    }
    @Override
    public Page<OrderVO> findTeamSendOrder(String teamId, OrderQueryParam queryParam, Pageable pageable) {
        BooleanExpression predicate = QueryManager.resolvePredicate(queryParam);
        QOrder order = QOrder.order;
        predicate = predicate.and(order.fromTeamId.eq(teamId));
        Page<Order> page = orderRepository.findAll(predicate, pageable);
        return page.map(this::toVO);
    }

}
