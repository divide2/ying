package com.ying.order.service;

import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.ConnectService;
import com.ying.friend.vo.FriendVO;
import com.ying.order.model.Order;
import com.ying.product.dto.InStockDTO;
import com.ying.product.dto.OutStockDTO;
import com.ying.product.model.ProductSpec;
import com.ying.product.vo.ProductVO;

/**
 * @author bvvy
 * @date 2018/12/15
 */
public interface OrderConnectService extends ConnectService {
    FriendVO getOnlyFriend(Integer userId, Integer toId);

    /**
     * 获取用户
     *
     * @param userId user id
     * @return user
     */
    UserVO getUser(Integer userId);

    ProductVO getProductById(String productId);

    void sendMessage(Order order);

    ProductSpec getProductSpec(String productSpecId);

    /**
     * 出库
     *
     * @param outStock stock
     */
    void outStock(OutStockDTO outStock);

    /**
     * 入库
     *
     * @param inStockDTO stock
     */
    void inStock(InStockDTO inStockDTO);

}
