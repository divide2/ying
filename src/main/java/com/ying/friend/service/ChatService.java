package com.ying.friend.service;

import com.ying.core.basic.service.BasicService;
import com.ying.friend.model.Chat;
import com.ying.friend.vo.ChatVO;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/30
 */
public interface ChatService extends BasicService<Chat, String> {

    List<ChatVO> listByUser(Integer userId);

}
