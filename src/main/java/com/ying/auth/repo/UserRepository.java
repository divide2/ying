package com.ying.auth.repo;


import com.ying.auth.model.User;
import com.ying.auth.repo.cutom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author bvvy
 * 基础user repo
 */
public interface UserRepository extends UserRepositoryCustom, JpaRepository<User, Integer>,QuerydslPredicateExecutor<User> {

    /**
     * 通过用户名获取用户
     * @param username 用户名
     * @return principal
     */
    User getByUsername(String username);

    /**
     * 通过微信openid获取用户
     * @param openid openid
     * @return user
     */
    User getByWechatOpenId(String openid);
}
