package com.divide2.mine.service;

import com.divide2.team.vo.TeamVO;
import com.divide2.friend.dto.MessageDTO;
import com.divide2.friend.query.MessageQuery;
import com.divide2.friend.vo.ChatVO;
import com.divide2.friend.vo.FriendVO;
import com.divide2.friend.vo.MessageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/10
 */
public interface MineService {

    /**
     * 获取好友
     * @return x
     */
    List<FriendVO> listFriends();

    FriendVO getFriend(Integer friendId);


    /**
     * 获取历史聊天的人
     * @return chat
     */
    List<ChatVO> listChat();

    /**
     * 获取消息
     *
     * @param toUserId 和谁的
     * @param query query
     * @param pageable page
     * @return page message
     */
    Page<MessageVO> findMessage(Integer toUserId, MessageQuery query, Pageable pageable);

    /**
     * 发送消息
     * @param messageDTO dto
     */
    void sendMessage(MessageDTO messageDTO);

    List<TeamVO> listUserTeams();

}
