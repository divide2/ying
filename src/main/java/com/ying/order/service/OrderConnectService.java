package com.ying.order.service;

import com.ying.core.basic.service.ConnectService;
import com.ying.friend.vo.FriendVO;
import com.ying.order.model.Order;
import com.ying.product.vo.ProductVO;

/**
 * @author bvvy
 * @date 2018/12/15
 */
public interface OrderConnectService extends ConnectService {
    FriendVO getOnlyFriend(Integer userId, Integer toId);

    ProductVO getProductById(Integer productId);

    void sendMessage(Order order);
}
