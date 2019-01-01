package com.ying.friend.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.friend.model.Chat;
import com.ying.friend.repo.ChatRepository;
import com.ying.friend.service.ChatService;
import com.ying.friend.vo.ChatVO;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/30
 */
@Service
public class ChatServiceImpl extends SimpleBasicServiceImpl<Chat,String,ChatRepository> implements ChatService {

    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<ChatVO> listByUser(Integer userId) {
        List<Chat> chats = chatRepository.findByFromId(userId);

    }
}
