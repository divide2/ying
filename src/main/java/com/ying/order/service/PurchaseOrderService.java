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
     *
     * @param dto dto
     */
    void add(PurchaseOrderDTO dto);

}
