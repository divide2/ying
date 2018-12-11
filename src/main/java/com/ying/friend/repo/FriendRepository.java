package com.ying.friend.repo;

import com.ying.friend.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/11
 */
public interface FriendRepository extends JpaRepository<Friend,Integer> {

    /**
     * 获取这个人的好友
     * @param fromId fromid
     * @return friends
     */
    List<Friend> findByFromId(Integer fromId);
}
