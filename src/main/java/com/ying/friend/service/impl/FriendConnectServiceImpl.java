package com.ying.friend.service.impl;

import com.ying.auth.service.UserService;
import com.ying.auth.vo.UserVO;
import com.ying.friend.service.FriendConnectService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/12/15
 */
@Service
public class FriendConnectServiceImpl implements FriendConnectService {
    private final UserService userService;

    public FriendConnectServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserVO getUser(Integer userId) {
        return userService.getVO(userId);
    }
}
