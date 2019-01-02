package com.ying.friend.service;

import com.ying.core.basic.service.BasicService;
import com.ying.friend.dto.MessageDTO;
import com.ying.friend.model.Message;
import com.ying.friend.query.MessageQuery;
import com.ying.friend.vo.MessageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/12/30
 */
public interface MessageService extends BasicService<Message,String> {
    /**
     * 发送信息
     * @param fromId from id
     * todo 对内容处理
     * @param dto dto
     */
    void sendMessage(Integer fromId,MessageDTO dto);

    Page<MessageVO> findChatMessage(Integer fromId, Integer toId, MessageQuery query, Pageable pageable);
}
