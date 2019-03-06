package com.ying.team.service;

import com.ying.auth.dto.*;
import com.ying.team.dto.*;
import com.ying.team.model.Team;
import com.ying.team.vo.CooperationApplicationVO;
import com.ying.team.vo.TeamApplicationVO;
import com.ying.team.vo.MemberVO;
import com.ying.team.vo.TeamVO;
import com.ying.core.basic.service.BasicService;
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
     * @param dto dto
     */
    void confirmCooperation(TeamCooperationConfirmDTO dto);

    /**
     * 申请 合作
     * @param dto dto
     */

    void applyCooperation(TeamCooperationApplyDTO dto);

    /**
     * 团队合作申请
     * @param teamId teamId
     * @return teamId
     */
    List<CooperationApplicationVO> listTeamCooperationApplication(String teamId);

    /**
     * 获取合作伙伴
     * @param teamId team
     * @return team
     */
    List<TeamVO> listTeamCooperations(String teamId);

    /**
     * 获取团队的仓库
     * @return vo
     */
    List<WarehouseVO> listWarehouse(String teamId);

    /**
     * 库存
     * @param stockQuery q
     * @param pageable p
     * @return x
     */
    Page<StockVO> findStock(String teamId, StockQuery stockQuery, Pageable pageable);
    /**
     * 获取团队产品
     * @param pageable p
     * @return x
     */
    Page<ProductVO> findProduct(String teamId, Pageable pageable);


    /**
     * 获取收到的订单
     * @param queryParam query
     * @param pageable pageable
     * @return x
     */
    Page<OrderVO> findReceiveOrder(String teamId, OrderQueryParam queryParam, Pageable pageable);


    /**
     * 获取发送的订单
     * @param queryParam query
     * @param pageable page
     * @return vo
     */
    Page<OrderVO> findSendOrder(String teamId, OrderQueryParam queryParam, Pageable pageable);


    /**
     * 获取用户的Team
     * @param userId userId
     * @return teams
     */
    List<TeamVO> listUserTeams(Integer userId);

}
