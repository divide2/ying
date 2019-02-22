package com.ying.friend.service;

import com.ying.friend.dto.ChatDTO;
import com.ying.friend.vo.ChatVO;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/30
 */
public interface ChatService{

    void save(ChatDTO dto);

    List<ChatVO> listByUser(Integer userId);

}
