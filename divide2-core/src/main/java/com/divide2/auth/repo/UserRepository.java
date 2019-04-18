package com.divide2.auth.repo;


import com.divide2.auth.model.User;
import com.divide2.auth.repo.custom.UserRepositoryCustom;
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


    /**
     * 通过手机号获取
     * @param query
     * @return
     */
    User getByPhone(String query);
}
