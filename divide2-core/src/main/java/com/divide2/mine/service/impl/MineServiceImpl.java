package com.divide2.mine.service.impl;

import com.divide2.team.service.TeamService;
import com.divide2.team.vo.TeamVO;
import com.divide2.core.er.Loginer;
import com.divide2.friend.dto.MessageDTO;
import com.divide2.friend.query.MessageQuery;
import com.divide2.friend.service.ChatService;
import com.divide2.friend.service.FriendService;
import com.divide2.friend.service.MessageService;
import com.divide2.friend.vo.ChatVO;
import com.divide2.friend.vo.FriendVO;
import com.divide2.friend.vo.MessageVO;
import com.divide2.mine.service.MineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/10
 */
@Service
public class MineServiceImpl implements MineService {

    private final FriendService friendService;
    private final ChatService chatService;
    private final MessageService messageService;
    private final TeamService teamService;

    public MineServiceImpl(
            FriendService friendService,
            ChatService chatService,
            MessageService messageService,
            TeamService teamService) {
        this.friendService = friendService;
        this.chatService = chatService;
        this.messageService = messageService;
        this.teamService = teamService;
    }

    @Override
    public Page<MessageVO> findMessage(Integer toUserId, MessageQuery query, Pageable pageable) {
        return messageService.findChatMessage(Loginer.userId(), toUserId, query, pageable);
    }

    @Override
    public void sendMessage(MessageDTO messageDTO) {
        messageDTO.setFromId(Loginer.userId());
        messageService.sendMessage(messageDTO);
    }

    @Override
    public List<ChatVO> listChat() {
        return chatService.listByUser(Loginer.userId());
    }



    @Override
    public List<FriendVO> listFriends() {
        return friendService.listByFromId(Loginer.userId());
    }

    @Override
    public FriendVO getFriend(Integer friendId) {
        return friendService.getVO(Loginer.userId(), friendId);
    }


    @Override
    public List<TeamVO> listUserTeams() {
        return teamService.listUserTeams(Loginer.userId());
    }

}
