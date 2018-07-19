package com.mj.core.service.impl;

import com.mj.core.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * 简单的实现
 */
public  class SimpleBasicServiceImpl<T, ID, DAO extends JpaRepository<T, ID>> implements BasicService<T, ID> {

    @Autowired
    private DAO dao;

    @Override
    public T add(T t) {
        return dao.save(t);
    }

    @Override
    public void delete(ID id) {
        dao.deleteById(id);
    }

    @Override
    public T update(T t) {
        return dao.save(t);
    }

    @Override
    public T get(ID id) {
        return dao.getOne(id);
    }

    @Override
    public Page<T> find(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }


}
