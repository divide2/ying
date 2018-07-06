package com.mj.auth.user.service;


import com.mj.auth.user.model.User;
import com.mj.core.service.BasicService;

/**
 * @author bvvy
 */
public interface UserService extends BasicService<User,Integer> {

    /**
     * 通过用户名获取用户
     * @param username p
     * @return user
     */
    User getByUsername(String username);

}
