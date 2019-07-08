package com.divide2.order.service.impl;

import com.divide2.auth.service.UserService;
import com.divide2.auth.vo.UserVO;
import com.divide2.friend.service.FriendService;
import com.divide2.friend.vo.FriendVO;
import com.divide2.order.model.Order;
import com.divide2.order.service.OrderConnectService;
import com.divide2.product.dto.InStockDTO;
import com.divide2.product.dto.OutStockDTO;
import com.divide2.product.model.ProductSpec;
import com.divide2.product.service.ProductService;
import com.divide2.product.service.ProductSpecService;
import com.divide2.product.service.StockService;
import com.divide2.product.vo.ProductVO;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/12/15
 */
@Service
public class OrderConnectServiceImpl implements OrderConnectService {
    private final FriendService friendService;
    private final ProductService productService;
    private final UserService userService;
    private final ProductSpecService productSpecService;
    private final StockService stockService;


    public OrderConnectServiceImpl(FriendService friendService,
                                   ProductService productService,
                                   UserService userService, ProductSpecService productSpecService,
                                   StockService stockService) {
        this.friendService = friendService;
        this.productService = productService;

        this.userService = userService;
        this.productSpecService = productSpecService;
        this.stockService = stockService;
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
    public ProductVO getProductById(String productId) {
        return productService.getVO(productId);
    }

    @Override
    public void sendMessage(Order order) {

    }

    @Override
    public ProductSpec getProductSpec(String productSpecId) {
        return productSpecService.get(productSpecId);
    }

    @Override
    public void outStock(OutStockDTO outStock) {
        stockService.out(outStock);
    }

    @Override
    public void inStock(InStockDTO inStockDTO) {
        stockService.in(inStockDTO);
    }

}
