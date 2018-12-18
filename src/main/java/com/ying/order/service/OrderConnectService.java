package com.ying.order.service;

import com.ying.auth.vo.UserVO;
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

    /**
     * 获取用户
     * @param userId useri d
     * @return user
     */
    UserVO getUser(Integer userId);

    ProductVO getProductById(Integer productId);

    void sendMessage(Order order);

    /**
     * 添加采购记录 和销售记录相反人员信息
     * @param order order
     */
    void addPurchaseOrder(Order order);

    /**
     * 添加销售记录
     * 和采购记录相反的人员信息
     * @param order order
     */
    void addSellOrder(Order order);
}
