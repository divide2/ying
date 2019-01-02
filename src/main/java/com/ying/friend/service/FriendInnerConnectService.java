package com.ying.friend.service;

import com.ying.friend.dto.ChatDTO;
import com.ying.friend.vo.FriendVO;

/**
 * @author bvvy
 * @date 2019/1/2
 */
public interface FriendInnerConnectService {

    FriendVO getFriend(Integer fromId, Integer toId);

    void addChat(ChatDTO chatDTO);
}
