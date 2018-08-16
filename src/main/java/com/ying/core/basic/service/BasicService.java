package com.ying.core.basic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author bvvy
 * <p>
 * 基础的增删改查的接口，提供一种规范
 * 目前只提供这几个方法 其他方法需要自己在各自的接口中写并实现
 * T model的类型
 * ID model的id类型
 */
public interface BasicService<T, ID> {

    /**
     * 添加
     *
     * @param t 添加的对象
     * @return t
     */
    T add(T t);

    /**
     * 删除
     *
     * @param id id
     */
    void delete(ID id);

    /**
     * 修改
     *
     * @param t   修改的对象
     * @return s
     */
    T update(T t);


    /**
     * 通过id获取一个
     *
     * @param id id
     * @return S
     */
    T get(ID id);

    /**
     * 获取分页后数据
     * todo 如何设计
     *
     * @param pageable 分页
     * @return page
     */
    Page<T> find(Pageable pageable);

    /**
     * 查询所有数据
     *
     * @return 所有
     */
    List<T> findAll();
}
