package com.hlsb.repository;


import com.hlsb.model.QUser;
import com.hlsb.model.UserCountOfClz;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.CollectionExpression;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bvvy on 2017/12/4.
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserCountOfClz> someCustomMethod() {
        QUser qUser = QUser.user;
        JPAQuery<UserCountOfClz> query = new JPAQuery<>(entityManager);
        List<Tuple> users = query.select(NumberExpression.sz    .as("userNum"), qUser.clzId, qUser.clzName)
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
