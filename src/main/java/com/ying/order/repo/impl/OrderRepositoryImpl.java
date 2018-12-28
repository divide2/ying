package com.ying.order.repo.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import com.ying.core.basic.custom.impl.SimpleBasicCustomRepositoryImpl;
import com.ying.core.root.query.QueryManager;
import com.ying.order.model.QOrder;
import com.ying.order.model.QPurchaseOrder;
import com.ying.order.model.QSellOrder;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.repo.custom.OrderRepositoryCustom;
import com.ying.order.vo.OrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public Page<OrderVO> findPurchaseOrderByUser(Integer userId, OrderQueryParam query, Pageable pageable) {
        JPAQuery<OrderVO> jpaQuery = super.createQuery();
        QOrder o = QOrder.order;
        QPurchaseOrder po = QPurchaseOrder.purchaseOrder;
        QBean<OrderVO> qbean = Projections.bean(OrderVO.class, o.attachment, o.balancePayment, o.createTime,
                o.deliveryDate, o.earnestMoney, o.remarks, o.status, o.id.as("orderId"), po.toName);
        jpaQuery.select(qbean).from(po).leftJoin(o).on(po.orderId.eq(o.id)).where(o.fromId.eq(userId));
        return findPage(jpaQuery, pageable);
    }

    @Override
    public Page<OrderVO> findSellOrderByUser(Integer userId, OrderQueryParam query, Pageable pageable) {
        JPAQuery<OrderVO> jpaQuery = super.createQuery();
        QOrder o = QOrder.order;
        QSellOrder so = QSellOrder.sellOrder;
        QBean<OrderVO> qbean = Projections.bean(OrderVO.class, o.attachment, o.balancePayment, o.createTime,
                o.deliveryDate, o.earnestMoney, o.remarks, o.status, o.id.as("orderId"), so.toName);
        jpaQuery.select(qbean).from(so).leftJoin(o).on(so.orderId.eq(o.id)).where(o.fromId.eq(userId)).where(QueryManager.resolvePredicate(query));

        return findPage(jpaQuery, pageable);
    }
}
