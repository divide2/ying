package com.divide2.core.basic.custom;

import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/8/6
 */
public interface BasicCustomRepository {



    /**
     * 通过sql来查分页 有的查询通过query dsl 很难完成 需要使用 sql直接查询
     * @param sql sql
     * @param clz 分页后的对象/dto的class
     * @param pageable page
     * @param params 查询参数
     * @return page
     */
    <T>Page<T> findBySql(String sql, Class<T> clz, Pageable pageable, Object... params);


    /**
     *
     * @param jpqlQuery jpalQuery
     * @param pageable pageable
     * @param <T> T
     * @param mainPath 需要排序的path  todo 需要生成sort
     * @return page
     */

    <T> Page<T> findPage(JPQLQuery<T> jpqlQuery, Pageable pageable, Path<T> mainPath);

    /**
     * 获取分页 获取dto
     * @param jpqlQuery jpalQuery
     * @param pageable pageable
     * @param <T> T
     * @return page
     */
    <T> Page<T> findPage(JPQLQuery<T> jpqlQuery, Pageable pageable);
}
