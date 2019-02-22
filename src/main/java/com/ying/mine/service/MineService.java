package com.ying.mine.service;

import com.ying.team.vo.TeamVO;
import com.ying.friend.dto.MessageDTO;
import com.ying.friend.query.MessageQuery;
import com.ying.friend.vo.ChatVO;
import com.ying.friend.vo.FriendVO;
import com.ying.friend.vo.MessageVO;
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
