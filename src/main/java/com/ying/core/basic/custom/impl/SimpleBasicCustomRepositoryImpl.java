package com.ying.core.basic.custom.impl;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.ying.core.basic.custom.BasicCustomRepository;
import com.ying.order.model.QOrder;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.querydsl.QSort;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/8/6
 */
public class SimpleBasicCustomRepositoryImpl implements BasicCustomRepository {

    protected final EntityManager entityManager;

    public SimpleBasicCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Session getSession() {
        return (Session) entityManager.getDelegate();
    }

    @Override
    public long queryCount(JPAQuery<?> query, Pageable pageable) {
        return query.limit(pageable.getPageSize()).offset(pageable.getOffset()).fetchCount();
    }

    @Override
    public <T> Page<T> findBySql(String sql, Class<T> clz, Pageable pageable, Object... params) {
        List<T> resultList = this.results(sql, clz, pageable, params);
        int count = this.count(sql, params);
        return new PageImpl<>(resultList, pageable, count);
    }


    private int count(String sql, Object... params) {
        String countSql = String.format("select count(*) from (%s)", sql);
        NativeQuery query = getSession().createNativeQuery(countSql);
        this.setParams(query, params);
        return query.getFirstResult();
    }

    private <T> List<T> results(String sql, Class<T> clz, Pageable pageable, Object... params) {
        NativeQuery query = getSession().createNativeQuery(sql);
        query.setFirstResult(pageable.getPageNumber()).setMaxResults(Long.valueOf(pageable.getOffset()).intValue());
        this.setParams(query, params);
        query.setResultTransformer(Transformers.aliasToBean(clz));
        return query.getResultList();
    }

    private void setParams(Query query, Object[] params) {
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
    }

    /**
     * Applies sorting to the given {@link JPQLQuery}.
     *
     * @param sort
     * @param query must not be {@literal null}.
     * @return the Querydsl {@link JPQLQuery}
     */
    public <T> JPQLQuery<T> applySorting(Sort sort, JPQLQuery<T> query) {

        if (sort.isUnsorted()) {
            return query;
        }

        if (sort instanceof QSort) {
            return addOrderByFrom((QSort) sort, query);
        }

        return addOrderByFrom(sort, query);
    }

    /**
     * Applies the given {@link OrderSpecifier}s to the given {@link JPQLQuery}. Potentially transforms the given
     * {@code OrderSpecifier}s to be able to injection potentially necessary left-joins.
     *
     * @param qsort must not be {@literal null}.
     * @param query must not be {@literal null}.
     */
    private <T> JPQLQuery<T> addOrderByFrom(QSort qsort, JPQLQuery<T> query) {

        List<OrderSpecifier<?>> orderSpecifiers = qsort.getOrderSpecifiers();
        return query.orderBy(orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]));
    }

    /**
     * Converts the {@link Sort.Order} items of the given {@link Sort} into {@link OrderSpecifier} and attaches those to the
     * given {@link JPQLQuery}.
     *
     * @param sort must not be {@literal null}.
     * @param query must not be {@literal null}.
     * @return
     */
    private <T> JPQLQuery<T> addOrderByFrom(Sort sort, JPQLQuery<T> query) {

        Assert.notNull(sort, "Sort must not be null!");
        Assert.notNull(query, "Query must not be null!");

        for (Sort.Order order : sort) {
            query.orderBy(toOrderSpecifier(order));
        }

        return query;
    }

    /**
     * Transforms a plain {@link Sort.Order} into a QueryDsl specific {@link OrderSpecifier}.
     *
     * @param order must not be {@literal null}.
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private OrderSpecifier<?> toOrderSpecifier(Sort.Order order) {

        return new OrderSpecifier(
                order.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC,
                buildOrderPropertyPathFrom(order), toQueryDslNullHandling(order.getNullHandling()));
    }

    /**
     * Converts the given {@link org.springframework.data.domain.Sort.NullHandling} to the appropriate Querydsl
     * {@link OrderSpecifier.NullHandling}.
     *
     * @param nullHandling must not be {@literal null}.
     * @return
     * @since 1.6
     */
    private OrderSpecifier.NullHandling toQueryDslNullHandling(org.springframework.data.domain.Sort.NullHandling nullHandling) {

        Assert.notNull(nullHandling, "NullHandling must not be null!");

        switch (nullHandling) {

            case NULLS_FIRST:
                return OrderSpecifier.NullHandling.NullsFirst;

            case NULLS_LAST:
                return OrderSpecifier.NullHandling.NullsLast;

            case NATIVE:
            default:
                return OrderSpecifier.NullHandling.Default;
        }
    }

    /**
     * Creates an {@link Expression} for the given {@link Sort.Order} property.
     *
     * @param order must not be {@literal null}.
     * @return
     */
    private Expression<?> buildOrderPropertyPathFrom(Sort.Order order) {

        Assert.notNull(order, "Order must not be null!");

        QOrder qOrder = QOrder.order;
        PropertyPath path = PropertyPath.from(order.getProperty(), qOrder.getType());
        Expression<?> sortPropertyExpression = qOrder;

        while (path != null) {

            if (!path.hasNext() && order.isIgnoreCase()) {
                // if order is ignore-case we have to treat the last path segment as a String.
                sortPropertyExpression = Expressions.stringPath((Path<?>) sortPropertyExpression, path.getSegment()).lower();
            } else {
                sortPropertyExpression = Expressions.path(path.getType(), (Path<?>) sortPropertyExpression, path.getSegment());
            }

            path = path.next();
        }

        return sortPropertyExpression;
    }
}
