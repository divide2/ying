package com.hlsb.repository;

import com.hlsb.model.QUser;
import com.hlsb.model.User;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

/**
 * Created by bvvy on 2017/12/4.
 * com.hlsb.repo
 */
public interface UserRepository extends
        UserRepositoryCustom,
        JpaRepository<User, Integer>,
        QueryDslPredicateExecutor<User>
        {
/*
    @Override
    default void customize(QuerydslBindings bindings, QUser user) {
        bindings.bind(user.username).first((StringExpression::contains));
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(user.password);
    }*/
}
