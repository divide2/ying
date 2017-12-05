package com.hlsb.service;

import com.hlsb.model.User;
import com.hlsb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bvvy on 2017/12/4.
 * com.hlsb.service
 */
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public void somce() {
        userRepository.someCustomMethod(new User());
    }

}
