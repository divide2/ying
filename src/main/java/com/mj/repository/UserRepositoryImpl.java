package com.mj.repository;


import com.mj.model.QUser;
import com.mj.model.UserCountOfClz;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2017/12/4
 *
 * repo
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserCountOfClz> someCustomMethod() {
        QUser qUser = QUser.user;
        JPAQuery<UserCountOfClz> query = new JPAQuery<>(entityManager);
        List<Tuple> users = query.select(qUser.count().as("userNum"), qUser.clzId, qUser.clzName)
                .from(qUser)
                .groupBy(qUser.clzId).fetch();

        return users.stream().map(
                tuple -> UserCountOfClz
                        .builder()
                        .clzId(tuple.get(qUser.clzId))
                        .clzName(tuple.get(qUser.clzName))
                        .build()

        ).collect(Collectors.toList());
    }

}
