package com.ying.auth.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.ying.auth.dto.UserQueryDTO;
import com.ying.auth.dto.UserSearchDTO;
import com.ying.auth.model.QUser;
import com.ying.auth.model.User;
import com.ying.auth.repo.UserGroupRoleRepository;
import com.ying.auth.repo.UserRepository;
import com.ying.auth.service.AuthConnectService;
import com.ying.auth.service.UserService;
import com.ying.auth.vo.UserGroupVO;
import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.exception.AlreadyExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final AuthConnectService authConnectService;

    private final UserGroupRoleRepository userGroupRoleRepository;


    public UserServiceImpl(AuthConnectService authConnectService, UserGroupRoleRepository userGroupRoleRepository) {
        this.authConnectService = authConnectService;
        this.userGroupRoleRepository = userGroupRoleRepository;
    }

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

    @Override
    public User getByWechatOpenId(String openid) {
        return userRepository.getByWechatOpenId(openid);
    }

    @Override
    public UserVO getVO(Integer userId) {
        User user = userRepository.getOne(
                userId);
        return UserVO.fromUser(user);
    }

    @Override
    public UserVO search(UserSearchDTO search) {
        User user = userRepository.getByPhone(search.getQuery());
        if (user == null) {
            return null;
        }
        return UserVO.fromUser(user);
    }

    @Override
    @Transactional
    public UserVO getByAccount(String account) {
        User user = userRepository.getByAccount(account);
        return UserVO.fromUser(user);
    }

    @Override
    public List<UserGroupVO> listUserGroup(Integer userId) {
        return userRepository.listUserGroup(userId);
    }

}
