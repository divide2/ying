package com.mj.auth.user.service.impl;

import com.mj.auth.user.model.User;
import com.mj.auth.user.repo.UserRepository;
import com.mj.auth.user.service.UserService;
import com.mj.core.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        User existsUser = getByUsername(user.getPhone());
        if (existsUser != null) {
            throw new AlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getByUsername(String phone) {
        return userRepository.getByUsername(phone);
    }


}
