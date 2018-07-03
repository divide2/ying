package com.mj.auth.user.service;


import com.mj.auth.user.model.User;

public interface UserService {
    void save(User user);

    User getByUsername(String phone);

}
