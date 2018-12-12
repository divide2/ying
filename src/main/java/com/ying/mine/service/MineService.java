package com.ying.mine.service;

import com.ying.friend.service.impl.FriendVO;
import com.ying.mine.vo.WarehouseVO;
import com.ying.product.model.Product;
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
    /**
     * 获取用户的仓库
     * @return vo
     */
    List<WarehouseVO> listWarehouse();

    Page<StockVO> findStock(StockQuery stockQuery, Pageable pageable);

    List<FriendVO> listFriends();

    List<ProductVO> listProduct();


}
