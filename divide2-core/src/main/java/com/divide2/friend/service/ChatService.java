package com.divide2.friend.service;

import com.divide2.friend.dto.ChatDTO;
import com.divide2.friend.dto.TeamMenuChatDTO;
import com.divide2.friend.dto.SimpleMenuChatDTO;
import com.divide2.friend.vo.ChatVO;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/30
 */
public interface ChatService{

    void save(ChatDTO dto);

    List<ChatVO> listByUser(Integer userId);

    void addMenuChat(TeamMenuChatDTO chat);

    void addMenuChat(SimpleMenuChatDTO dto);
}
