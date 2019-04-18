package com.divide2.auth.repo.custom;

import com.divide2.auth.model.User;

/**
 * @author bvvy
 * <p>
 * 用户repo
 */
public interface UserRepositoryCustom {



    /**
     * 通过登录账号获取用户
     * @param username 账号
     * @return user
     */
    User getByAccount(String username);



}
