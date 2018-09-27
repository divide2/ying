package com.ying.auth.principal.repo.impl;


import com.ying.auth.principal.model.QRole;
import com.ying.auth.principal.model.QUser;
import com.ying.auth.principal.model.QUserRole;
import com.ying.auth.principal.model.User;
import com.ying.auth.principal.repo.cutom.UserRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.JpaQueryCreator;
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
        JPAQuery<SimpleGrantedAuthority> query = new JPAQuery<>(entityManager);
        return query.select(Projections.constructor(SimpleGrantedAuthority.class, role.code))
                .from(user).leftJoin(ur).on(user.id.eq(ur.userId)).leftJoin(role).on(role.id.eq(ur.roleId))
                .where(user.username.eq(username)).fetch();

    }


    @Override
    public List<User> findUsersByRole(Integer roleId) {
        QUser user = QUser.user;
        QUserRole ur = QUserRole.userRole;
        JPAQuery<User> query = new JPAQuery<>(entityManager);
        return query.select(user).from(ur).innerJoin(user).on(ur.userId.eq(user.id))
                        .where(ur.roleId.eq(roleId)).fetch();
    }

    @Override
    public User getByLogin(String username) {

        QUser user = QUser.user;
        JPAQuery<User> query = new JPAQuery<>(entityManager);
        query.where(user.username.eq(username).or(user.phone.eq(username).or(user.email.eq(username)))).fetch();
        return null;
    }
}
