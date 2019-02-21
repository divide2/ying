package com.ying.friend.repo;

import com.ying.friend.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/30
 */
public interface ChatRepository extends JpaRepository<Chat, String> {


    List<Chat> findByUserId(Integer userId);

    Chat getByUserIdAndTarget(Integer fromId, String toId);
}
