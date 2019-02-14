package com.ying.friend.repo;

import com.ying.friend.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/14
 */

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    /**
     * 查申请信息
     *
     * @param fromId 申请人
     * @param toId   对方
     * @return 申请信息
     */
    Application getByFromIdAndToId(Integer fromId, Integer toId);

    default List<Application> findUserApplications(Integer userId) {
        return this.findByFromIdOrToIdOrderByUpdateTimeDesc(userId, userId);
    }

    List<Application> findByFromIdOrToIdOrderByUpdateTimeDesc(Integer fromId, Integer toId);

}
