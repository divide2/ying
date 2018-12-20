package com.ying.order.repo.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import com.ying.core.basic.custom.impl.SimpleBasicCustomRepositoryImpl;
import com.ying.order.model.QOrder;
import com.ying.order.model.QPurchaseOrder;
import com.ying.order.query.OrderQuery;
import com.ying.order.repo.custom.OrderRepositoryCustom;
import com.ying.order.vo.OrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;

/**
 * @author bvvy
 * @date 2018/12/19
 */
public class OrderRepositoryImpl extends SimpleBasicCustomRepositoryImpl implements OrderRepositoryCustom {

    public OrderRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Page<OrderVO> findPurchaseOrderByUser(Integer userId, OrderQuery query, Pageable pageable) {
        JPAQuery<?> jpaQuery = new JPAQuery<>(entityManager);
        QOrder o = QOrder.order;
        QPurchaseOrder po = QPurchaseOrder.purchaseOrder;
        QBean<OrderVO> qbean = Projections.bean(OrderVO.class, o.attachment, o.balancePayment, o.createTime,
                o.deliveryDate, o.earnestMoney, o.remarks, o.status, o.id.as("orderId"), po.toName);
        jpaQuery.select(qbean)
                .from(po).leftJoin(o).on(po.orderId.eq(o.id)).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        Sort sort = pageable.getSort();
        return null;
    }

    @Override
    public Page<OrderVO> findSellOrderByUser(Integer userId, OrderQuery query, Pageable pageable) {
        return null;
    }
}