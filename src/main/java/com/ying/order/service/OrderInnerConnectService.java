package com.ying.order.service;

import com.ying.core.basic.service.ConnectService;
import com.ying.order.model.Order;

/**
 * @author bvvy
 * @date 2018/12/18
 */
public interface OrderInnerConnectService extends ConnectService {
    /**
     * 添加采购记录 和销售记录相反人员信息
     * @param order order
     */
    void addPurchaseOrder(Order order);

    /**
     * 添加销售记录
     * 和采购记录相反的人员信息
     * @param order order
     */
    void addSellOrder(Order order);

}
