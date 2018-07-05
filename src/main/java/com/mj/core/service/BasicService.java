package com.mj.core.service;

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
     * @param s   添加的对象
     * @param <S> 实体的类 Model
     * @return s
     */
    <S extends T> S add(S s);

    /**
     * 删除
     *
     * @param id id
     */
    void delete(ID id);

    /**
     * 修改
     *
     * @param s   修改的对象
     * @param <S> 实体的类型 Model
     * @return s
     */
    <S extends T> S update(S s);


    /**
     * 通过id获取一个
     *
     * @param id  id
     * @return S
     */
    T get(ID id);

    /**
     * 获取分页后数据
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
