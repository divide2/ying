package com.ying.auth.repo.impl;


import com.ying.auth.model.QRole;
import com.ying.auth.model.QUser;
import com.ying.auth.model.QUserRole;
import com.ying.auth.model.User;
import com.ying.auth.repo.cutom.UserRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author bvvy
 */
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
    public User getByAccount(String username) {

        QUser user = QUser.user;
        JPAQuery<User> query = new JPAQuery<>(entityManager);
        User loginUser = query.where(user.username.eq(username).or(user.phone.eq(username).or(user.email.eq(username)))).fetchOne();
        return loginUser;
    }

}
