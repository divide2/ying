package com.ying.client.service.impl;

import com.ying.auth.service.UserService;
import com.ying.auth.vo.UserVO;
import com.ying.client.dto.UserInfoVO;
import com.ying.client.service.ClientService;
import com.ying.core.er.Loginer;
import com.ying.core.exception.ValidationException;
import com.ying.friend.service.FriendService;
import com.ying.friend.vo.FriendVO;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2019/2/14
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final FriendService friendService;
    private final UserService userService;

    public ClientServiceImpl(FriendService friendService,
                             UserService userService) {
        this.friendService = friendService;
        this.userService = userService;
    }

    @Override
    public UserInfoVO getUserInfo(Integer userId) {
        UserVO user = userService.getVO(userId);
        UserInfoVO userInfo = new UserInfoVO();
        userInfo.setAvatar(user.getAvatar());
        userInfo.setEmail(user.getEmail());
        userInfo.setGender(user.getGender());
        userInfo.setNickname(user.getNickname());
        userInfo.setPhone(user.getPhone());
        userInfo.setUserId(user.getUserId());
        userInfo.setUsername(user.getUsername());
        userInfo.setFriend(false);
        FriendVO friend = friendService.getVO(Loginer.userId(), userId);
        if (friend != null) {
            userInfo.setFriend(true);
            userInfo.setMemoName(friend.getMemoName());
        }
        return userInfo;
    }

}
