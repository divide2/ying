package com.mj.auth.user.service;


import com.mj.auth.user.model.User;

/**
 * @author bvvy
 */
public interface UserService {
    /**
     * 保存用户
     * @param user userr
     */
    void save(User user);

    /**
     * 通过用户名获取用户
     * @param username p
     * @return user
     */
    User getByUsername(String username);

}
