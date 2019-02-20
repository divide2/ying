package com.ying.auth.repo;

import com.ying.auth.model.GroupCooperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/20
 */
public interface GroupCooperationRepository extends JpaRepository<GroupCooperation, String> {

    GroupCooperation getByFromGroupIdAndToGroupId(String fromGroupId, String toGroupId);

    /**
     * 企业的合作企业
     * @param groupId groupId
     * @return groups
     */
    List<GroupCooperation> findByFromGroupId(String groupId);
}
