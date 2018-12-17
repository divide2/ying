package com.ying.friend.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.order.dto.OrderDTO;
import com.ying.order.model.Order;
import com.ying.order.repo.OrderRepository;
import com.ying.order.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/17
 */
@Service
public class OrderServiceImpl extends SimpleBasicServiceImpl<Order,Integer, OrderRepository> implements OrderService {

    @Override
    public void add(OrderDTO dto) {

    }
}
