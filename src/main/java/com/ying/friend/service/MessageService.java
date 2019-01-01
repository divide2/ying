package com.ying.friend.service;

import com.ying.friend.dto.MessageDTO;

/**
 * @author bvvy
 * @date 2018/12/30
 */
public interface MessageService {
    /**
     * 发送信息
     * todo 对内容处理
     * @param dto dto
     */
    void sendMessage(MessageDTO dto);
}
