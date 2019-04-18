package com.divide2.friend.service.impl;

import com.divide2.auth.service.UserService;
import com.divide2.auth.vo.UserVO;
import com.divide2.friend.dto.SimpleMenuChatDTO;
import com.divide2.friend.service.ChatService;
import com.divide2.friend.service.FriendConnectService;
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
