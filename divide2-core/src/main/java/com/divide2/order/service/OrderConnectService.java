package com.divide2.order.service;

import com.divide2.auth.vo.UserVO;
import com.divide2.core.basic.service.ConnectService;
import com.divide2.friend.vo.FriendVO;
import com.divide2.order.model.Order;
import com.divide2.product.dto.InStockDTO;
import com.divide2.product.dto.OutStockDTO;
import com.divide2.product.model.ProductSpec;
import com.divide2.product.vo.ProductVO;

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
