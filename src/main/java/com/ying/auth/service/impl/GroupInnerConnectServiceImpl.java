package com.ying.auth.service.impl;

import com.ying.auth.service.GroupInnerConnectService;
import com.ying.auth.service.MenuService;
import com.ying.auth.service.RoleService;
import com.ying.auth.service.UserService;
import com.ying.auth.vo.MenuVO;
import com.ying.auth.vo.RoleVO;
import com.ying.auth.vo.UserVO;
import com.ying.friend.dto.ChatDTO;
import com.ying.friend.service.ChatService;
import com.ying.mine.vo.WarehouseVO;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.service.OrderService;
import com.ying.order.vo.OrderVO;
import com.ying.product.model.Warehouse;
import com.ying.product.query.StockQuery;
import com.ying.product.service.ProductService;
import com.ying.product.service.StockService;
import com.ying.product.service.WarehouseService;
import com.ying.product.vo.ProductVO;
import com.ying.product.vo.StockVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2019/2/17
 */
@Service
public class GroupInnerConnectServiceImpl implements GroupInnerConnectService {

    private final UserService userService;
    private final RoleService roleService;
    private final OrderService orderService;
    private final WarehouseService warehouseService;
    private final ProductService productService;
    private final StockService stockService;
    private final ChatService chatService;
    private final MenuService menuService;

    public GroupInnerConnectServiceImpl(UserService userService, RoleService roleService, OrderService orderService, WarehouseService warehouseService, ProductService productService, StockService stockService, ChatService chatService, MenuService menuService) {
        this.userService = userService;
        this.roleService = roleService;
        this.orderService = orderService;
        this.warehouseService = warehouseService;
        this.productService = productService;
        this.stockService = stockService;
        this.chatService = chatService;
        this.menuService = menuService;
    }

    @Override
    public UserVO getUser(Integer userId) {
        return userService.getVO(userId);
    }

    @Override
    public RoleVO getRole(Integer roleId) {
        return roleService.getVO(roleId);
    }


    @Override
    public Page<OrderVO> findReceiveOrder(String groupId,OrderQueryParam queryParam, Pageable pageable) {
        return orderService.findGroupReceiveOrder(groupId, queryParam, pageable);
    }


    @Override
    public Page<OrderVO> findSendOrder(String groupId,OrderQueryParam queryParam, Pageable pageable) {
        return orderService.findGroupSendOrder(groupId, queryParam, pageable);
    }

    @Override
    public List<WarehouseVO> listWarehouse(String groupId) {
        List<Warehouse> warehouses = warehouseService.listByGroup(groupId);
        return warehouses.stream().map(warehouse -> {
            WarehouseVO vo = new WarehouseVO();
            vo.setId(warehouse.getId());
            vo.setName(warehouse.getName());
            vo.setType(warehouse.getType());
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public Page<StockVO> findStock(String groupId,StockQuery stockQuery, Pageable pageable) {
        return stockService.findByGroup(groupId,stockQuery, pageable);
    }


    @Override
    public Page<ProductVO> findProduct(String groupId,Pageable pageable) {
        return productService.findByGroup(groupId, pageable);
    }

    @Override
    public void addChat(ChatDTO chat) {
        chatService.save(chat);
    }

    @Override
    public MenuVO getMenu(String menuCode) {
        return menuService.getByCode(menuCode);
    }

    @Override
    public List<UserVO> listGroupOwnMenuUsers(String groupId, Integer menuId) {
        return null;
    }
}
