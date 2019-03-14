package com.ying.team.service.impl;


import com.ying.auth.service.UserService;
import com.ying.auth.vo.UserVO;
import com.ying.friend.dto.TeamMenuChatDTO;
import com.ying.friend.service.ChatService;
import com.ying.mine.vo.WarehouseVO;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.service.OrderService;
import com.ying.order.vo.OrderVO;
import com.ying.product.query.StockQuery;
import com.ying.product.service.ProductService;
import com.ying.product.service.StockService;
import com.ying.product.service.WarehouseService;
import com.ying.product.vo.ProductVO;
import com.ying.product.vo.StockVO;
import com.ying.team.service.AclService;
import com.ying.team.service.MenuService;
import com.ying.team.service.SquadService;
import com.ying.team.service.TeamInnerConnectService;
import com.ying.team.vo.MenuVO;
import com.ying.team.vo.SquadVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author bvvy
 * @date 2019/2/17
 */
@Service
public class TeamInnerConnectServiceImpl implements TeamInnerConnectService {

    private final UserService userService;
    private final SquadService squadService;
    private final OrderService orderService;
    private final WarehouseService warehouseService;
    private final ProductService productService;
    private final StockService stockService;
    private final ChatService chatService;
    private final MenuService menuService;
    private final AclService aclService;

    public TeamInnerConnectServiceImpl(
            UserService userService,
            SquadService squadService,
            OrderService orderService,
            WarehouseService warehouseService,
            ProductService productService,
            StockService stockService,
            ChatService chatService,
            MenuService menuService,
            AclService aclService) {

        this.userService = userService;
        this.squadService = squadService;
        this.orderService = orderService;
        this.warehouseService = warehouseService;
        this.productService = productService;
        this.stockService = stockService;
        this.chatService = chatService;
        this.menuService = menuService;
        this.aclService = aclService;
    }

    @Override
    public UserVO getUser(Integer userId) {
        return userService.getVO(userId);
    }

    @Override
    public SquadVO getSquad(String squadId) {
        return squadService.getVO(squadId);
    }


    @Override
    public Page<OrderVO> findReceiveOrder(String teamId, OrderQueryParam queryParam, Pageable pageable) {
        return orderService.findTeamReceiveOrder(teamId, queryParam, pageable);
    }


    @Override
    public Page<OrderVO> findSendOrder(String teamId, OrderQueryParam queryParam, Pageable pageable) {
        return orderService.findTeamSendOrder(teamId, queryParam, pageable);
    }

    @Override
    public List<WarehouseVO> listWarehouse(String teamId) {
        return warehouseService.listByTeam(teamId);
    }

    @Override
    public Page<StockVO> findStock(String teamId, StockQuery stockQuery, Pageable pageable) {
        return stockService.findByTeam(teamId, stockQuery, pageable);
    }


    @Override
    public Page<ProductVO> findProduct(String teamId, Pageable pageable) {
        return productService.findByTeam(teamId, pageable);
    }

    @Override
    public void addChat(TeamMenuChatDTO chat) {
        chatService.addMenuChat(chat);
    }

    @Override
    public MenuVO getMenu(String menuId) {
        return menuService.getVO(menuId);
    }

    @Override
    public List<UserVO> listTeamOwnMenuUsers(String teamId, Integer menuId) {
        return null;
    }

    @Override
    public Set<String> listTeamUserMenuIds(String teamId, Integer userId) {
        Set<String> authorities = aclService.listTeamUserAuthorities(teamId, userId);
        return menuService.findByMenuIdsByAuthorities(authorities);
    }

    @Override
    public List<SquadVO> listSquadByTeam(String teamId) {
        return squadService.listByTeam(teamId);
    }

}
