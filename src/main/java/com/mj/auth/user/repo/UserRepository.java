package com.mj.auth.user.repo;


import com.mj.auth.user.model.User;
import com.mj.auth.user.repo.cutom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * 基础user repo
 */
public interface UserRepository extends UserRepositoryCustom, JpaRepository<User, Integer> {

    /**
     * 通过用户名获取用户
     * @param username 用户名
     * @return user
     */
    User getByUsername(String username);
}
