package com.ying.friend.repo.custom;

import com.ying.friend.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2019/1/2
 */
public interface MessageRepositoryCustom  {

    /**
     *
     * @param fromId from id
     * @param toId todi
     * @param pageable page
     * @return page
     */
    Page<Message> findChatMessage(Integer fromId, Integer toId, Pageable pageable);

}
