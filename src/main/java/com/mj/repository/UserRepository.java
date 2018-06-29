package com.mj.repository;

import com.mj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 *
 * @author bvvy
 * @date 2017/12/4
 */
public interface UserRepository extends
        UserRepositoryCustom,
        JpaRepository<User, Integer>,
        QuerydslPredicateExecutor<User>
        {
/*
    @Override
    default void customize(QuerydslBindings bindings, QUser user) {
        bindings.bind(user.username).first((StringExpression::contains));
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(user.password);
    }*/
}
