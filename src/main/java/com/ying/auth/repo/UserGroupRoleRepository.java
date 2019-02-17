package com.ying.auth.repo;

import com.ying.auth.model.UserGroupRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bvvy
 */
public interface UserGroupRoleRepository extends JpaRepository<UserGroupRole, Integer> {

    /**
     * 通过角色id来删除
     *
     * @param roleId roleId
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteByRoleId(Integer roleId);

    /**
     * 用户在团队下的角色
     *
     * @param userId  userid
     * @param groupId groupid
     */
    UserGroupRole getByUserIdAndGroupId(Integer userId, String groupId);


    /**
     * 团队下的人员
     *
     * @param groupId 团队
     * @return info
     */
    List<UserGroupRole> findByGroupId(String groupId);

}
