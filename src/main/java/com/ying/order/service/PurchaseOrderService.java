package com.ying.order.service;

import com.ying.core.basic.service.BasicService;
import com.ying.order.dto.PurchaseOrderDTO;
import com.ying.order.model.Order;
import com.ying.order.model.PurchaseOrder;
import com.ying.order.query.PurchaseOrderQuery;
import com.ying.order.vo.PurchaseOrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/12/2
 */
public interface PurchaseOrderService extends BasicService<PurchaseOrder,Integer> {
    /**
     * 通过order来添加
     * @param order order
     */
    void add(Order order);

    /**
     * 用户的销售单
     * @param userId 用户
     * @param query query
     * @param pageable pageable
     * @return page
     */
    Page<PurchaseOrderVO> findByUser(Integer userId, PurchaseOrderQuery query, Pageable pageable);
}
