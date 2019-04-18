package com.divide2.friend.repo;

import com.divide2.friend.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/30
 */
public interface ChatRepository extends JpaRepository<Chat, String> {


    List<Chat> findByUserId(Integer userId);

    Chat getByUserIdAndTargetAndType(Integer userId, String target, String type);
}
