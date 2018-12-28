package com.ying.order.service.impl;

import com.ying.order.model.OrderProductSpec;
import com.ying.order.repo.OrderProductSpecRepository;
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
    public List<OrderProductSpec> listByOrder(Integer orderId) {
        return orderProductSpecRepository.findByOrderId(orderId);
    }

}
