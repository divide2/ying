package com.mj.auth.user.service.impl;

import com.mj.auth.user.model.User;
import com.mj.auth.user.repo.UserRepository;
import com.mj.auth.user.service.UserService;
import com.mj.core.exception.AlreadyExistsException;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class UserServiceImpl extends SimpleBasicServiceImpl<User,Integer,UserRepository> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(User user) {
        User existsUser = getByUsername(user.getPhone());
        if (existsUser != null) {
            throw new AlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }


}
