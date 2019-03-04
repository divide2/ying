package com.ying.order.service;

import com.ying.core.basic.service.BasicService;
import com.ying.order.dto.PurchaseOrderDTO;
import com.ying.order.model.PurchaseOrder;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.vo.OrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/12/2
 */
public interface PurchaseOrderService extends BasicService<PurchaseOrder,String> {
    /**
     * 通过order来添加
     * @param dto purchase
     */
    void add(PurchaseOrderDTO dto);

    /**
     * 用户的采购单
     * @param userId 用户
     * @param query query
     * @param pageable pageable
     * @return page
     */
    Page<OrderVO> findByUser(Integer userId, OrderQueryParam query, Pageable pageable);
}
