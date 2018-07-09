package com.mj.biz.demo.repo;

import com.mj.biz.demo.model.QStudent;
import com.mj.biz.demo.model.Student;
import com.mj.biz.demo.repo.custom.StudentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

/**
 * @author bvvy
 * @date 学生的repo
 */
public interface StudentRepository extends StudentRepositoryCustom,
        JpaRepository<Student, Integer>,
        QuerydslPredicateExecutor<Student>,
        QuerydslBinderCustomizer<QStudent> {
    /**
     * xx
     * @param bindings sxx
     * @param root xx
     */
    @Override
    default void customize(QuerydslBindings bindings, QStudent root) {
        bindings.bind(root.name).first((path, value) -> path.like("%" + value + "%"));

    }

}
