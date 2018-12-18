package com.ying.order.service;

import com.ying.core.basic.service.BasicService;
import com.ying.order.dto.OrderConfirmDTO;
import com.ying.order.dto.OrderDTO;
import com.ying.order.model.Order;

/**
 * @author bvvy
 * @date 2018/12/17
 */
public interface OrderService extends BasicService<Order, Integer> {

    /**
     * 1 添加 订单
     * 2.记录 采购单
     *
     * @param dto dto
     */
    void add(OrderDTO dto);


    /**
     * 确认订单
     *
     * @param confirm confirm
     */
    void confirm(OrderConfirmDTO confirm);
}
