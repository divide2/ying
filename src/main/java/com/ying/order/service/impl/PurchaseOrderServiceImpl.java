package com.ying.order.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.order.dto.PurchaseOrderDTO;
import com.ying.order.model.PurchaseOrder;
import com.ying.order.repo.PurchaseOrderRepository;
import com.ying.order.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Service
public class PurchaseOrderServiceImpl extends SimpleBasicServiceImpl<PurchaseOrder,Integer, PurchaseOrderRepository> implements PurchaseOrderService {
    @Override
    public void add(PurchaseOrderDTO dto) {

    }
}
