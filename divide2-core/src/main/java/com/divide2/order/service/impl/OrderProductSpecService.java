package com.divide2.order.service.impl;

import com.divide2.order.model.OrderProductSpec;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/27
 */
public interface OrderProductSpecService {

    List<OrderProductSpec> listByOrder(String orderId);

}
