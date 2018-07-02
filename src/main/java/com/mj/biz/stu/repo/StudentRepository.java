package com.mj.biz.stu.repo;

import com.mj.biz.stu.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date
 * 学生的repo
 */
public interface StudentRepository extends StudentRepositoryCustom,JpaRepository<Student,Integer> {

}
