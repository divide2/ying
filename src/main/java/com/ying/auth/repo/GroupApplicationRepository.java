package com.ying.auth.repo;

import com.ying.auth.model.GroupApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/17
 */
public interface GroupApplicationRepository extends JpaRepository<GroupApplication, String> {

    GroupApplication getByFromIdAndToGroupId(Integer userId, String toGroupId);

    /**
     * 查某个团队下的申请
     * @param groupId  groupId
     * @return application
     */
    List<GroupApplication> findByToGroupIdOrderByUpdateTimeDesc(String groupId);


}
