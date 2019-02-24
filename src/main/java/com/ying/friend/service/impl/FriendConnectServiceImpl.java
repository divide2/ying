package com.ying.friend.service.impl;

import com.ying.auth.service.UserService;
import com.ying.auth.vo.UserVO;
import com.ying.friend.dto.SimpleMenuChatDTO;
import com.ying.friend.service.ChatService;
import com.ying.friend.service.FriendConnectService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/12/15
 */
@Service
public class FriendConnectServiceImpl implements FriendConnectService {
    private final UserService userService;
    private final ChatService chatService;

    public FriendConnectServiceImpl(UserService userService,
                                    ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;
    }

    @Override
    public UserVO getUser(Integer userId) {
        return userService.getVO(userId);
    }

    @Override
    public UserVO getUserByAccount(String account) {
        return userService.getByAccount(account);
    }

    @Override
    public void addChat(SimpleMenuChatDTO dto) {
        chatService.addMenuChat(dto);
    }
}
