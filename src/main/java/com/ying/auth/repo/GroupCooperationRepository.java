package com.ying.auth.repo;

import com.ying.auth.model.GroupCooperation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2019/2/20
 */
public interface GroupCooperationRepository extends JpaRepository<GroupCooperation, String> {

    GroupCooperation getByFromGroupIdAndToGroupId(String fromGroupId, String toGroupId);
}
