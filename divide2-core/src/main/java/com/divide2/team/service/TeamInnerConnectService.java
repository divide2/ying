package com.divide2.team.service;

import com.divide2.auth.vo.UserVO;
import com.divide2.core.basic.service.ConnectService;
import com.divide2.friend.dto.TeamMenuChatDTO;
import com.divide2.mine.vo.WarehouseVO;
import com.divide2.order.query.OrderQueryParam;
import com.divide2.order.vo.OrderVO;
import com.divide2.product.query.StockQuery;
import com.divide2.product.vo.ProductVO;
import com.divide2.product.vo.StockStreamVO;
import com.divide2.product.vo.StockVO;
import com.divide2.team.vo.MenuVO;
import com.divide2.team.vo.SquadVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * @author bvvy
 * @date 2019/2/17
 */
public interface TeamInnerConnectService extends ConnectService {
    UserVO getUser(Integer userId);

    SquadVO getSquad(String squadId);

    /**
     * 获取团队的仓库
     *
     * @param teamId team
     * @return vo
     */
    List<WarehouseVO> listWarehouse(String teamId);

    /**
     * 库存
     *
     * @param teamId     team
     * @param stockQuery q
     * @param pageable   p
     * @return x
     */
    Page<StockVO> findStock(String teamId, StockQuery stockQuery, Pageable pageable);

    Page<StockStreamVO> findStockStream(String teamId, StockQuery stockQuery, Pageable pageable);

    /**
     * 获取团队产品
     *
     * @param teamId   team
     * @param pageable p
     * @return x
     */
    Page<ProductVO> findProduct(String teamId, Pageable pageable);


    /**
     * 获取收到的订单
     *
     * @param teamId     team
     * @param queryParam query
     * @param pageable   pageable
     * @return x
     */
    Page<OrderVO> findReceiveOrder(String teamId, OrderQueryParam queryParam, Pageable pageable);


    /**
     * 获取发送的订单
     *
     * @param teamId     team
     * @param queryParam query
     * @param pageable   page
     * @return vo
     */
    Page<OrderVO> findSendOrder(String teamId, OrderQueryParam queryParam, Pageable pageable);


    void addChat(TeamMenuChatDTO chat);

    MenuVO getMenu(String menuId);

    List<UserVO> listTeamOwnMenuUsers(String teamId, Integer menuId);

    /**
     * 获取用户拥有的菜单id
     *
     * @param teamId teamId
     * @param userId userId
     * @return menuIds
     */
    Set<String> listTeamUserChildrenMenuIds(String teamId, String type, String userId);

    List<SquadVO> listSquadByTeam(String teamId);

    Set<String> listMemberAuthorities(String teamId);
}
