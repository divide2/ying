package com.ying.mine.service;

import com.ying.friend.vo.FriendVO;
import com.ying.mine.vo.WarehouseVO;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.vo.OrderVO;
import com.ying.product.query.StockQuery;
import com.ying.product.vo.ProductVO;
import com.ying.product.vo.StockVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/10
 */
public interface MineService {
    Page<OrderVO> findSellOrder(OrderQueryParam query, Pageable pageable);

    /**
     * 获取用户的仓库
     * @return vo
     */
    List<WarehouseVO> listWarehouse();

    /**
     * 库存
     * @param stockQuery q
     * @param pageable p
     * @return x
     */
    Page<StockVO> findStock(StockQuery stockQuery, Pageable pageable);

    /**
     * 获取好友
     * @return x
     */
    List<FriendVO> listFriends();

    /**
     * 获取自己的产品
     * @param pageable p
     * @return x
     */
    Page<ProductVO> findProduct(Pageable pageable);

    Page<ProductVO> findCompanyProduct(Pageable pageable);

    /**
     * 获取收到的订单
     * @param queryParam query
     * @param pageable pageable
     * @return x
     */
    Page<OrderVO> findReceiveOrder(OrderQueryParam queryParam, Pageable pageable);

    Page<OrderVO> findPurchaseOrder(OrderQueryParam query, Pageable pageable);
}
