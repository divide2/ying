package com.ying.auth.repo.impl;


import com.querydsl.jpa.impl.JPAQuery;
import com.ying.auth.model.QUser;
import com.ying.auth.model.User;
import com.ying.auth.repo.cutom.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * @author bvvy
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private EntityManager entityManager;
    private QUser user = QUser.user;

    @Override
    public User getByAccount(String username) {

        JPAQuery<User> query = new JPAQuery<>(entityManager);
        return query.from(user)
                .where(user.username.eq(username)
                        .or(user.phone.eq(username))
                        .or(user.email.eq(username))).fetchOne();
    }

}
