package com.divide2.team.service;

import com.divide2.auth.dto.UserSearchDTO;
import com.divide2.core.basic.service.BasicService;
import com.divide2.mine.vo.WarehouseVO;
import com.divide2.order.query.OrderQueryParam;
import com.divide2.order.vo.OrderVO;
import com.divide2.product.query.StockQuery;
import com.divide2.product.vo.ProductVO;
import com.divide2.product.vo.StockStreamVO;
import com.divide2.product.vo.StockVO;
import com.divide2.team.dto.*;
import com.divide2.team.model.Team;
import com.divide2.team.vo.CooperationApplicationVO;
import com.divide2.team.vo.MemberVO;
import com.divide2.team.vo.TeamApplicationVO;
import com.divide2.team.vo.TeamVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * @author bvvy
 * @date 2019/2/13
 */
public interface TeamService extends BasicService<Team, String> {

    void add(TeamAddDTO dto);

    /**
     * 获取团队下的成员
     *
     * @param teamId 团队
     * @return 团队成员
     */
    List<MemberVO> listMembers(String teamId);

    TeamVO getVO(String teamId);

    TeamVO search(UserSearchDTO search);

    /**
     * 获取用户能放问的菜单id
     *
     * @param teamId      team
     * @param type        类型
     * @param principleId user/squadid
     * @return menuIds
     */
    Set<String> listTeamUserMenus(String teamId, String type, String principleId);

    /**
     * todo 通知
     *
     * @param dto
     */
    void apply(TeamApplyDTO dto);

    /**
     * todo 通知
     *
     * @param dto
     */
    void confirm(TeamConfirmDTO dto);


    List<TeamApplicationVO> listTeamApplications(String teamId);


    /**
     * 确认建立何总
     *
     * @param dto dto
     */
    void confirmCooperation(TeamCooperationConfirmDTO dto);

    /**
     * 申请 合作
     *
     * @param dto dto
     */

    void applyCooperation(TeamCooperationApplyDTO dto);

    /**
     * 团队合作申请
     *
     * @param teamId teamId
     * @return teamId
     */
    List<CooperationApplicationVO> listTeamCooperationApplication(String teamId);

    /**
     * 获取合作伙伴
     *
     * @param teamId team
     * @return team
     */
    List<TeamVO> listTeamCooperations(String teamId);

    /**
     * 获取团队的仓库
     *
     * @return vo
     */
    List<WarehouseVO> listWarehouse(String teamId);


    /**
     * 获取团队产品
     *
     * @param pageable p
     * @return x
     */
    Page<ProductVO> findProduct(String teamId, Pageable pageable);


    /**
     * 获取收到的订单
     *
     * @param teamId team
     * @param queryParam query
     * @param pageable   pageable
     * @return x
     */
    Page<OrderVO> findReceiveOrder(String teamId, OrderQueryParam queryParam, Pageable pageable);


    /**
     * 获取发送的订单
     *
     * @param queryParam query
     * @param pageable   page
     * @return vo
     */
    Page<OrderVO> findSendOrder(String teamId, OrderQueryParam queryParam, Pageable pageable);


    /**
     * 获取用户的Team
     *
     * @param userId userId
     * @return teams
     */
    List<TeamVO> listUserTeams(Integer userId);

    /**
     * 获取用户的权限
     *
     * @param teamId teamId
     * @return x
     */
    Set<String> listMemberAuthorities(String teamId);

    /**
     * 库存
     *
     * @param stockQuery q
     * @param pageable   p
     * @return x
     */
    Page<StockVO> findStock(String teamId, StockQuery stockQuery, Pageable pageable);

    /**
     * 团队库存流水
     * @param teamId t
     * @param stockQuery sq
     * @param pageable p
     * @return x
     */
    Page<StockStreamVO> findStockStream(String teamId, StockQuery stockQuery, Pageable pageable);


}
