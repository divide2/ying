package com.hlsb.service;

import com.hlsb.model.UserCountOfClz;
import com.hlsb.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bvvy on 2017/12/4.
 * com.hlsb.service
 */
@Service("userServiceImpl")
public class UserService implements IUserService{

    @Resource
    private UserRepository userRepository;
    @Override
    public List<UserCountOfClz> somce() {
        return userRepository.someCustomMethod();
    }


}
