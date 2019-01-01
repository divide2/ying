package com.ying.friend.repo;

import com.ying.friend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/30
 */
public interface MessageRepository extends JpaRepository<Message, String> {

}
