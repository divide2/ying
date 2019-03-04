 package com.ying.order.service;

 import com.ying.core.basic.service.BasicService;
 import com.ying.core.data.del.SingleStringId;
 import com.ying.order.dto.OrderDTO;
 import com.ying.order.dto.OrderDeliverDTO;
 import com.ying.order.dto.OrderReceiveDTO;
 import com.ying.order.model.Order;
 import com.ying.order.query.OrderQueryParam;
 import com.ying.order.vo.OrderVO;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/12/17
 */
public interface OrderService extends BasicService<Order, String> {

    /**
     * 1 添加 订单
     * 2.记录 采购单
     *
     * @param dto dto
     */
    void add(OrderDTO dto);


    /**
     * 确认订单
     *
     * @param confirm confirm
     */
    void confirm(SingleStringId confirm);

    /**
     * 确认发货
     * @param deliver delivery
     */
    void confirmDeliver(OrderDeliverDTO deliver);

    /**
     * 确认收货
     * @param receive receive
     */
    void confirmReceive(OrderReceiveDTO receive);

    /**
     * 获取用户收到的订单
     * @param teamId teamId
     * @param queryParam query
     * @param pageable pageable
     * @return page
     */
    Page<OrderVO> findTeamReceiveOrder(String teamId, OrderQueryParam queryParam, Pageable pageable);


    /**
     * 获取用户发送的订单
     * @param teamId teamId
     * @param queryParam query
     * @param pageable pageable
     * @return page
     */
    Page<OrderVO> findTeamSendOrder(String teamId, OrderQueryParam queryParam, Pageable pageable);

}
