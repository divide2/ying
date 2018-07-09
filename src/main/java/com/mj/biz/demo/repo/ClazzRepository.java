package com.mj.biz.demo.repo;

import com.mj.biz.demo.model.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * 班级repo
 */
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {

    Clazz findByName(String name);
}
