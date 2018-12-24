package com.ying.order.service;

import com.ying.core.basic.service.BasicService;
import com.ying.order.dto.PurchaseOrderDTO;
import com.ying.order.model.SellOrder;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.vo.OrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/12/18
 */
public interface SellOrderService  extends BasicService<SellOrder,Integer> {

    void add(PurchaseOrderDTO dto);

    /**
     * 用户的销售单
     * @param userId useid
     * @param query q
     * @param pageable pageable
     * @return order vo
     */
    Page<OrderVO> findByUser(Integer userId, OrderQueryParam query, Pageable pageable);
}
