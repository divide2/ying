package com.divide2.client.service.impl;

import com.divide2.auth.service.UserService;
import com.divide2.auth.vo.UserVO;
import com.divide2.client.dto.UserInfoVO;
import com.divide2.client.service.ClientService;
import com.divide2.core.er.Loginer;
import com.divide2.friend.service.FriendService;
import com.divide2.friend.vo.FriendVO;
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
