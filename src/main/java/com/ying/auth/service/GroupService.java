package com.ying.auth.service;

import com.ying.auth.dto.*;
import com.ying.auth.model.Group;
import com.ying.auth.vo.*;
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
public interface GroupService extends BasicService<Group, String> {

    void add(GroupAddDTO dto);

    /**
     * 获取团队下的成员
     *
     * @param groupId 团队
     * @return 团队成员
     */
    List<GroupUserVO> listGroupUsers(String groupId);

    GroupVO getVO(String groupId);

    GroupVO search(UserSearchDTO search);
    /**
     * todo 通知
     *
     * @param dto
     */
    void apply(GroupApplyDTO dto);

    /**
     * todo 通知
     *
     * @param dto
     */
    void confirm(GroupConfirmDTO dto);



    List<GroupApplicationVO> listGroupApplications(String groupId);


    /**
     * 确认建立何总
     * @param dto dto
     */
    void confirmCooperation(GroupCooperationConfirmDTO dto);

    /**
     * 申请 合作
     * @param dto dto
     */

    void applyCooperation(GroupCooperationApplyDTO dto);

    /**
     * 团队合作申请
     * @param groupId groupId
     * @return
     */
    List<CooperationApplicationVO> listGroupCooperationApplication(String groupId);

    /**
     * 获取合作伙伴
     * @param groupId group
     * @return group
     */
    List<GroupVO> listGroupCooperations(String groupId);

    /**
     * 获取团队的仓库
     * @return vo
     */
    List<WarehouseVO> listWarehouse(String groupId);

    /**
     * 库存
     * @param stockQuery q
     * @param pageable p
     * @return x
     */
    Page<StockVO> findStock(String groupId,StockQuery stockQuery, Pageable pageable);
    /**
     * 获取团队产品
     * @param pageable p
     * @return x
     */
    Page<ProductVO> findProduct(String groupId,Pageable pageable);


    /**
     * 获取收到的订单
     * @param queryParam query
     * @param pageable pageable
     * @return x
     */
    Page<OrderVO> findReceiveOrder(String groupId,OrderQueryParam queryParam, Pageable pageable);


    /**
     * 获取发送的订单
     * @param queryParam query
     * @param pageable page
     * @return vo
     */
    Page<OrderVO> findSendOrder(String groupId,OrderQueryParam queryParam, Pageable pageable);


}
