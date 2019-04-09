package com.ying.auth.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.ying.auth.dto.PwdFindDTO;
import com.ying.auth.dto.UserQueryDTO;
import com.ying.auth.dto.UserSearchDTO;
import com.ying.auth.dto.UserUpdateDTO;
import com.ying.auth.model.QUser;
import com.ying.auth.model.User;
import com.ying.auth.repo.UserRepository;
import com.ying.auth.service.UserService;
import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.er.Loginer;
import com.ying.core.exception.AlreadyExistsException;
import com.ying.core.exception.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        User user = userRepository.getOne(userId);

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
        if (user == null) {
            throw new NotFoundException();
        }
        return UserVO.fromUser(user);
    }

    @Override
    public void findPwd(PwdFindDTO pwdFind) {
        User user = userRepository.getByPhone(pwdFind.getPhoneNumber());
        if (user == null) {
            throw new NotFoundException();
        }
        user.setPassword(pwdFind.getPassword());
        this.update(user);
    }

    @Override
    public void update(UserUpdateDTO dto) {
        User user = this.get(Loginer.userId());
        user.setNickname(dto.getNickname());
        user.setAvatar(dto.getAvatar());
        this.update(user);
    }
}
