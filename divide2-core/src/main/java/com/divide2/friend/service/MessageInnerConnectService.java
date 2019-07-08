package com.divide2.friend.service;

import com.divide2.core.basic.service.ConnectService;
import com.divide2.friend.dto.ChatDTO;
import com.divide2.friend.vo.FriendVO;

/**
 * @author bvvy
 * @date 2019/1/2
 */
public interface MessageInnerConnectService extends ConnectService {
    void addChat(ChatDTO chatDTO);

    FriendVO getFriend(Integer fromId, Integer toId);
}
