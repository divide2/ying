package com.ying.core.basic.custom.impl;

import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.ying.core.basic.custom.BasicCustomRepository;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
    public <T> Page<T> findBySql(String sql, Class<T> clz, Pageable pageable, Object... params) {
        List<T> resultList = this.results(sql, clz, pageable, params);
        int count = this.count(sql, params);
        return new PageImpl<>(resultList, pageable, count);
    }

    @Override
    public <T> Page<T> findPage(JPQLQuery<T> jpqlQuery, Pageable pageable, Path<T> mainPath) {
        long total = jpqlQuery.fetchCount();
        JPQLQuery<T> pagingQuery = this.applyPagination(pageable, jpqlQuery);
        return new PageImpl<T>(pagingQuery.fetch(), pageable, total);
    }

    @Override
    public <T> Page<T> findPage(JPQLQuery<T> jpqlQuery, Pageable pageable) {
        return findPage(jpqlQuery, pageable, null);
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

    private <T> JPQLQuery<T> applyPagination(Pageable pageable, JPQLQuery<T> query) {

        if (pageable.isUnpaged()) {
            return query;
        }

        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());
        return query;
        //todo to handle sort
    }



    protected  <T>JPAQuery<T> createQuery() {
        return new JPAQuery<>(entityManager);
    }

}
