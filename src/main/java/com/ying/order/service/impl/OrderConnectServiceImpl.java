package com.ying.order.service.impl;

import com.ying.auth.service.UserService;
import com.ying.auth.vo.UserVO;
import com.ying.friend.service.FriendService;
import com.ying.friend.vo.FriendVO;
import com.ying.order.model.Order;
import com.ying.order.service.OrderConnectService;
import com.ying.product.dto.InStockDTO;
import com.ying.product.dto.OutStockDTO;
import com.ying.product.model.ProductSpec;
import com.ying.product.service.ProductService;
import com.ying.product.service.ProductSpecService;
import com.ying.product.service.StockService;
import com.ying.product.vo.ProductVO;
import com.ying.team.service.TeamService;
import com.ying.team.vo.TeamVO;
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
    private final TeamService teamService;


    public OrderConnectServiceImpl(FriendService friendService,
                                   ProductService productService,
                                   UserService userService, ProductSpecService productSpecService,
                                   StockService stockService, TeamService teamService) {
        this.friendService = friendService;
        this.productService = productService;

        this.userService = userService;
        this.productSpecService = productSpecService;
        this.stockService = stockService;
        this.teamService = teamService;
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

    @Override
    public TeamVO getTeam(String teamId) {
        return teamService.getVO(teamId);
    }

}
