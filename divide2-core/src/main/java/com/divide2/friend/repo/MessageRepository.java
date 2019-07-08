package com.divide2.friend.repo;

import com.divide2.friend.model.Message;
import com.divide2.friend.repo.custom.MessageRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/30
 */
public interface MessageRepository extends JpaRepository<Message, String>, MessageRepositoryCustom {

    /**
     * 分页message
     * @param fromId from user Id
     * @param toId  to user id
     * @param pageable pageable
     * @return page message
     */
    Page<Message> findByFromIdAndToId(Integer fromId, Integer toId, Pageable pageable);

}
