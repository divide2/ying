package com.mj.biz.demo.repo;

import com.mj.biz.demo.model.Student;
import com.mj.biz.demo.repo.custom.StudentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date
 * 学生的repo
 */
public interface StudentRepository extends StudentRepositoryCustom,JpaRepository<Student,Integer> {

}
