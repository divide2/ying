package com.hlsb.service;

import com.hlsb.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by bvvy on 2017/12/4.
 * com.hlsb.service
 */
@Service("userServiceImpl")
public class UserService implements IUserService{

    @Resource
    private UserRepository userRepository;


    @Override
    public void somce() {
        userRepository.someCustomMethod("");
    }

}
