package com.ying.auth.service;

import com.ying.auth.vo.MenuVO;
import com.ying.auth.vo.RoleVO;
import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.ConnectService;
import com.ying.friend.dto.ChatDTO;
import com.ying.mine.vo.WarehouseVO;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.vo.OrderVO;
import com.ying.product.query.StockQuery;
import com.ying.product.vo.ProductVO;
import com.ying.product.vo.StockVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/17
 */
public interface GroupInnerConnectService extends ConnectService {
    UserVO getUser(Integer userId);

    RoleVO getRole(Integer roleId);

    /**
     * 获取团队的仓库
     *
     * @return vo
     */
    List<WarehouseVO> listWarehouse(String groupId);

    /**
     * 库存
     *
     * @param stockQuery q
     * @param pageable   p
     * @return x
     */
    Page<StockVO> findStock(String groupId, StockQuery stockQuery, Pageable pageable);

    /**
     * 获取团队产品
     *
     * @param pageable p
     * @return x
     */
    Page<ProductVO> findProduct(String groupId, Pageable pageable);


    /**
     * 获取收到的订单
     *
     * @param queryParam query
     * @param pageable   pageable
     * @return x
     */
    Page<OrderVO> findReceiveOrder(String groupId, OrderQueryParam queryParam, Pageable pageable);


    /**
     * 获取发送的订单
     *
     * @param queryParam query
     * @param pageable   page
     * @return vo
     */
    Page<OrderVO> findSendOrder(String groupId, OrderQueryParam queryParam, Pageable pageable);

    void addChat(ChatDTO chat);


    MenuVO getMenu(String menuCode);

    List<UserVO> listGroupOwnMenuUsers(String groupId, Integer menuId);
}
