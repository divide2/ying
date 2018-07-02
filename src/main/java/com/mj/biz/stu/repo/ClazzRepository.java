package com.mj.biz.stu.repo;

import com.mj.biz.stu.model.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * 班级repo
 */
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {

}
