package com.mj.auth.principal.service;


import com.mj.auth.principal.model.User;
import com.mj.core.service.BasicService;

import java.util.List;

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


    /**
     * 校验用户名
     * @param username 用户名
     */
    void validUsername(String username);


    /**
     * 获取角色所有用户
     * @param roleId roleId
     * @return users
     */
    List<User> findUsersByRole(Integer roleId);
}
