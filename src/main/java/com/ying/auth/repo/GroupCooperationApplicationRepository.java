package com.ying.auth.repo;

import com.ying.auth.model.GroupCooperationApplication;
import org.springframework.data.jpa.repository.JpaRepository;

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

}
