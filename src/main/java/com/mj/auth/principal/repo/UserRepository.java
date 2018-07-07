package com.mj.auth.principal.repo;


import com.mj.auth.principal.model.User;
import com.mj.auth.principal.repo.cutom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * 基础user repo
 */
public interface UserRepository extends UserRepositoryCustom, JpaRepository<User, Integer> {

    /**
     * 通过用户名获取用户
     * @param username 用户名
     * @return principal
     */
    User getByUsername(String username);
}
