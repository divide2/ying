package com.mj.auth.principal.service.impl;

import com.mj.auth.principal.dto.UserQueryDTO;
import com.mj.auth.principal.model.QUser;
import com.mj.auth.principal.model.User;
import com.mj.auth.principal.repo.UserRepository;
import com.mj.auth.principal.service.UserService;
import com.mj.core.exception.AlreadyExistsException;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 */
@Service
public class UserServiceImpl extends SimpleBasicServiceImpl<User, Integer, UserRepository> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(User user) {
        this.validUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public void validUsername(String username) {
        User existsUser = getByUsername(username);
        if (existsUser != null) {
            throw new AlreadyExistsException(username + "_exists");
        }
    }

    @Override
    public List<User> findUsersByRole(Integer roleId) {
        return userRepository.findUsersByRole(roleId);
    }

    @Override
    public Page<User> find(UserQueryDTO query, Pageable pageable) {
        BooleanExpression predicate = Expressions.ONE.eq(Expressions.ONE);
        QUser user = QUser.user;
        if (StringUtils.isNotEmpty(query.getKeyword())) {
            predicate = predicate.and(user.username.like("%" + query.getKeyword() + "%")
                    .or(user.nickname.like("%" + query.getKeyword() + "%")));
        }
        return userRepository.findAll(predicate, pageable);
    }

}
