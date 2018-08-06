package com.mj.core.basic.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/8/6
 */
public interface BasicCustomRepository {


    /**
     * 通过sql来查分页
     * @param sql sql
     * @param clz 分页后的对象/dto的class
     * @param pageable page
     * @param params 查询参数
     * @return page
     */
    <T>Page<T> findBySql(String sql, Class<T> clz, Pageable pageable, Object... params);
}
