package com.ying.order.repo.custom;

import com.ying.order.query.OrderQueryParam;
import com.ying.order.vo.OrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/12/19
 */
public interface OrderRepositoryCustom {

    /**
     * 获取采购单
     *
     * @param userId user
     * @param query query
     * @param pageable page
     * @return vo
     */
    Page<OrderVO> findPurchaseOrderByUser(Integer userId, OrderQueryParam query, Pageable pageable);

    /**
     * 获取销售单
     *
     * @param userId user
     * @param query query
     * @param pageable page
     * @return vo
     */
    Page<OrderVO> findSellOrderByUser(Integer userId, OrderQueryParam query, Pageable pageable);
}
