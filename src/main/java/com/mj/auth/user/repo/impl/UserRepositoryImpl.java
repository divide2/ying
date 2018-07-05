package com.mj.auth.user.repo.impl;


import com.mj.auth.user.model.QRole;
import com.mj.auth.user.model.QUser;
import com.mj.auth.user.model.QUserRole;
import com.mj.auth.user.repo.cutom.UserRepositoryCustom;
import com.mj.biz.demo.vo.StudentWithClazzNameVO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author bvvy
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<SimpleGrantedAuthority> findUserRolesByUsername(String username) {
        QUser user = QUser.user;
        QRole role = QRole.role;
        QUserRole ur = QUserRole.userRole;
        JPAQuery<StudentWithClazzNameVO> query = new JPAQuery<>(entityManager);
        return query.select(Projections.constructor(SimpleGrantedAuthority.class, role.code))
                .from(user).leftJoin(ur).on(user.id.eq(ur.userId)).leftJoin(role).on(role.id.eq(ur.roleId))
                .where(user.username.eq(username)).fetch();
    }

}
