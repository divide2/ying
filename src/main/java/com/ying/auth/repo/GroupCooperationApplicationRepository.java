package com.ying.auth.repo;

import com.ying.auth.model.GroupCooperationApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/20
 */
public interface GroupCooperationApplicationRepository extends JpaRepository<GroupCooperationApplication,String> {
    /**
     * 获取存在的申请
     * @param fromGroupId source
     * @param toGroupId  target
     * @return application
     */
    GroupCooperationApplication getByFromGroupIdAndToGroupId(String fromGroupId, String toGroupId);

     List<GroupCooperationApplication> findByFromGroupIdOrToGroupIdOrderByCreateTimeDesc(String fromGroupId, String toGroupId);

    default List<GroupCooperationApplication> findGroupCooperationApplications(String groupId) {
        return findByFromGroupIdOrToGroupIdOrderByCreateTimeDesc(groupId, groupId);
    }
}
