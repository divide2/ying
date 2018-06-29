package com.mj.service;

import com.mj.model.UserCountOfClz;
import com.mj.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author bvvy
 * @date 2017/12/4
 * com.mj.service
 */
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService{

    @Resource
    private UserRepository userRepository;
    @Override
    public List<UserCountOfClz> somce() {
        return userRepository.someCustomMethod();
    }


}
