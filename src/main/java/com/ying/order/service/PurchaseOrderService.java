package com.ying.order.service;

import com.ying.core.basic.service.BasicService;
import com.ying.order.dto.PurchaseOrderDTO;
import com.ying.order.model.PurchaseOrder;

/**
 * @author bvvy
 * @date 2018/12/2
 */
public interface PurchaseOrderService extends BasicService<PurchaseOrder,Integer> {
    /**
     * 添加 采购单
     * 第一步 添加一个采购单 采购商品和规格数量 为未确认状态 消息通知
     * 第二步 发送一个订单给好友
     * @param dto dto
     */
    void add(PurchaseOrderDTO dto);

}
