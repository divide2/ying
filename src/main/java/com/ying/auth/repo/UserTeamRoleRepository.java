package com.ying.auth.repo;

import com.ying.auth.model.UserTeamRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bvvy
 */
public interface UserTeamRoleRepository extends JpaRepository<UserTeamRole, Integer> {

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
     * @param teamId teamid
     */
    UserTeamRole getByUserIdAndTeamId(Integer userId, String teamId);


    /**
     * 团队下的人员
     *
     * @param teamId 团队
     * @return info
     */
    List<UserTeamRole> findByTeamId(String teamId);

}
