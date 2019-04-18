package com.divide2.order.service.impl;

import com.divide2.order.model.OrderProductSpec;
import com.divide2.order.repo.OrderProductSpecRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/27
 */
@Service
public class OrderProductSpecServiceImpl implements OrderProductSpecService {
    private final OrderProductSpecRepository orderProductSpecRepository;

    public OrderProductSpecServiceImpl(OrderProductSpecRepository orderProductSpecRepository) {
        this.orderProductSpecRepository = orderProductSpecRepository;
    }

    @Override
    public List<OrderProductSpec> listByOrder(String orderId) {
        return orderProductSpecRepository.findByOrderId(orderId);
    }

}
