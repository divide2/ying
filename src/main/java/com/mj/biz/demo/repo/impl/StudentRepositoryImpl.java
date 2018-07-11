package com.mj.biz.demo.repo.impl;


import com.mj.biz.demo.dto.StudentQueryDTO;
import com.mj.biz.demo.model.QClazz;
import com.mj.biz.demo.model.QStudent;
import com.mj.biz.demo.repo.custom.StudentRepositoryCustom;
import com.mj.biz.demo.vo.StudentWithClazzNameVO;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.querydsl.core.types.Projections.constructor;

/**
 * @author bvvy
 * @date 2017/12/4
 * repo
 */
@Repository
public class StudentRepositoryImpl implements StudentRepositoryCustom {

    private final EntityManager entityManager;

    public StudentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<StudentWithClazzNameVO> findWithClassName(StudentQueryDTO queryDTO, Pageable pageable) {
        QStudent student = QStudent.student;
        QClazz clazz = QClazz.clazz;
        JPAQuery<StudentWithClazzNameVO> query = new JPAQuery<>(entityManager);
         query.select(
                constructor(StudentWithClazzNameVO.class, student.id, student.name, student.gender, student.clazzId, clazz.name)
        ).from(student)
                .leftJoin(clazz)
                .on(student.clazzId.eq(clazz.id));
        Predicate userNameP  = student.name.like("");
        return null;
    }

    @Override
    public StudentWithClazzNameVO getWithClazzName(Integer id) {
        QStudent student = QStudent.student;
        QClazz clazz = QClazz.clazz;
        JPAQuery<StudentWithClazzNameVO> query = new JPAQuery<>(entityManager);
        return query.select(
                constructor(StudentWithClazzNameVO.class, student.id, student.name, student.gender, student.clazzId, clazz.name)
        ).from(student)
                .leftJoin(clazz)
                .on(student.clazzId.eq(clazz.id))
                .where(student.id.eq(id)).fetchOne();
    }
}
