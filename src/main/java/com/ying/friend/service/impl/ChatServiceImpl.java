package com.ying.friend.service.impl;

import com.ying.auth.vo.UserVO;
import com.ying.core.root.converter.Converter;
import com.ying.friend.dto.ChatDTO;
import com.ying.friend.dto.MenuChatDTO;
import com.ying.friend.model.Chat;
import com.ying.friend.repo.ChatRepository;
import com.ying.friend.service.ChatInnerConnectService;
import com.ying.friend.service.ChatService;
import com.ying.friend.vo.ChatVO;
import com.ying.friend.vo.FriendVO;
import com.ying.team.vo.MenuVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/30
 */
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    private final ChatInnerConnectService chatInnerConnectService;


    public ChatServiceImpl(ChatRepository chatRepository,
                           ChatInnerConnectService chatInnerConnectService) {
        this.chatRepository = chatRepository;
        this.chatInnerConnectService = chatInnerConnectService;
    }


    @Override
    public void save(ChatDTO dto) {
        // 发送方
        Chat chat = chatRepository.getByUserIdAndTargetAndType(dto.getUserId(), dto.getTarget(), dto.getType());
        if (chat == null) {
            chat = new Chat();
            chat.setUnreadCount(0);
            chat.setTarget(dto.getTarget());
            chat.setUserId(dto.getUserId());
            chat.setType(dto.getType());
            chat.setAvatar(dto.getAvatar());
            chat.setName(dto.getName());
            chat.setOrderNum(0);
        } else {
            chat.setUnreadCount(chat.getUnreadCount() + 1);
        }
        chat.setContent(dto.getContent());
        chat.setUpdateTime(LocalDateTime.now());

        chatRepository.save(chat);
    }


    @Override
    public void addMenuChat(MenuChatDTO chat) {

        // 获取功能菜单
        MenuVO menu = chatInnerConnectService.getMenu(chat.getMenuCode());
        // 获取该团队下管理这个功能的人
        List<UserVO> users = chatInnerConnectService.listTeamOwnMenuUsers(chat.getTeamId(), menu.getId());

        users.forEach(user ->
                this.save(new ChatDTO(user.getUserId(), menu.getId().toString(), "menu", menu.getName(), menu.getPath(), "你有新的申请")));
    }

    @Override
    public List<ChatVO> listByUser(Integer userId) {
        List<Chat> chats = chatRepository.findByUserId(userId);
        return Converter.of(chats).convert(chat -> {
            FriendVO friend = chatInnerConnectService.getFriend(userId, chat.getUserId());
            return ChatVO.builder()
                    .id(chat.getId())
                    .content(chat.getContent())
                    .updateTime(chat.getUpdateTime())
                    .avatar(friend.getAvatar())
                    .name(friend.getMemoName())
                    .toId(chat.getUserId())
                    .unreadCount(chat.getUnreadCount())
                    .build();
        });
    }
}
