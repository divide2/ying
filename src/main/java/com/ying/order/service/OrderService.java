package com.ying.order.service;

import com.ying.core.basic.service.BasicService;
import com.ying.order.dto.OrderDTO;
import com.ying.order.model.Order;

/**
 * @author bvvy
 * @date 2018/12/17
 */
public interface OrderService extends BasicService<Order,Integer> {

    void add(OrderDTO dto);

}
