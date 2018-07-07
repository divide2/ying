package com.mj.auth.principal.service;


import com.mj.auth.principal.model.User;
import com.mj.core.service.BasicService;

/**
 * @author bvvy
 */
public interface UserService extends BasicService<User,Integer> {

    /**
     * 通过用户名获取用户
     * @param username p
     * @return principal
     */
    User getByUsername(String username);

}
