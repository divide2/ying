package com.ying.auth.service;

import com.ying.auth.dto.GroupAddDTO;
import com.ying.auth.dto.GroupApplyDTO;
import com.ying.auth.dto.GroupConfirmDTO;
import com.ying.auth.model.Group;
import com.ying.auth.vo.GroupApplicationVO;
import com.ying.auth.vo.GroupUserVO;
import com.ying.auth.vo.GroupVO;
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
