package com.ying.order.service.impl;

import com.ying.auth.service.UserService;
import com.ying.auth.vo.UserVO;
import com.ying.friend.service.FriendService;
import com.ying.friend.vo.FriendVO;
import com.ying.order.dto.PurchaseOrderDTO;
import com.ying.order.model.Order;
import com.ying.order.service.OrderConnectService;
import com.ying.order.service.PurchaseOrderService;
import com.ying.order.service.SellOrderService;
import com.ying.product.service.ProductService;
import com.ying.product.vo.ProductVO;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/12/15
 */
@Service
public class OrderConnectServiceImpl implements OrderConnectService {
    private final FriendService friendService;
    private final ProductService productService;
    private final PurchaseOrderService purchaseOrderService;
    private final SellOrderService sellOrderService;
    private final UserService userService;


    public OrderConnectServiceImpl(FriendService friendService,
                                   ProductService productService,
                                   PurchaseOrderService purchaseOrderService,
                                   SellOrderService sellOrderService, UserService userService) {
        this.friendService = friendService;
        this.productService = productService;
        this.purchaseOrderService = purchaseOrderService;
        this.sellOrderService = sellOrderService;
        this.userService = userService;
    }

    @Override
    public UserVO getUser(Integer userId) {
        return userService.getVO(userId);
    }

    @Override
    public FriendVO getOnlyFriend(Integer fromId, Integer toId) {
        return friendService.getVO(fromId, toId);
    }

    @Override
    public ProductVO getProductById(Integer productId) {
        return productService.getVO(productId);
    }

    @Override
    public void sendMessage(Order order) {

    }

    @Override
    public void addPurchaseOrder(Order order) {
        purchaseOrderService.add(new PurchaseOrderDTO(order.getId(),order.getFromId(),order.getToId()));
    }

    @Override
    public void addSellOrder(Order order) {
        sellOrderService.add(new PurchaseOrderDTO(order.getId(), order.getToId(), order.getFromId()));
    }
}
