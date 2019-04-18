package com.divide2.friend.service.impl;

import com.divide2.core.root.converter.Converter;
import com.divide2.friend.dto.ChatDTO;
import com.divide2.friend.dto.SimpleMenuChatDTO;
import com.divide2.friend.dto.TeamMenuChatDTO;
import com.divide2.friend.model.Chat;
import com.divide2.friend.repo.ChatRepository;
import com.divide2.friend.service.ChatInnerConnectService;
import com.divide2.friend.service.ChatService;
import com.divide2.friend.vo.ChatVO;
import com.divide2.team.vo.MenuVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    public void addMenuChat(TeamMenuChatDTO chat) {

        // 获取功能菜单
        MenuVO menu = chatInnerConnectService.getMenu(chat.getMenuCode());
        // 获取该团队下管理这个功能的人
        Set<Integer> userIds = chatInnerConnectService.listTeamOwnMenuUserIds(chat.getTeamId(), menu.getAuthority());

        userIds.forEach(userId ->
                this.save(new ChatDTO(userId, menu.getId(), "menu", menu.getName(), menu.getIcon(), "你有新的申请")));
    }

    @Override
    public void addMenuChat(SimpleMenuChatDTO dto) {
        // 获取功能菜单
        MenuVO menu = chatInnerConnectService.getMenu(dto.getMenuCode());
        this.save(new ChatDTO(dto.getUserId(), menu.getId(), "menu", menu.getName(), menu.getIcon(), "你有新的申请"));
    }

    @Override
    public List<ChatVO> listByUser(Integer userId) {
        List<Chat> chats = chatRepository.findByUserId(userId);
        return Converter.of(chats).convert(chat -> ChatVO.builder()
                .id(chat.getId())
                .content(chat.getContent())
                .updateTime(chat.getUpdateTime())
                .avatar(chat.getAvatar())
                .name(chat.getName())
                .target(chat.getTarget())
                .type(chat.getType())
                .unreadCount(chat.getUnreadCount())
                .build());
    }
}
