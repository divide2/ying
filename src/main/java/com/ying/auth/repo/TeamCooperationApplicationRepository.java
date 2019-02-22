package com.ying.auth.repo;

import com.ying.auth.model.TeamCooperationApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/20
 */
public interface TeamCooperationApplicationRepository extends JpaRepository<TeamCooperationApplication,String> {
    /**
     * 获取存在的申请
     * @param fromTeamId source
     * @param toTeamId  target
     * @return application
     */
    TeamCooperationApplication getByFromTeamIdAndToTeamId(String fromTeamId, String toTeamId);

     List<TeamCooperationApplication> findByFromTeamIdOrToTeamIdOrderByCreateTimeDesc(String fromTeamId, String toTeamId);

    default List<TeamCooperationApplication> findTeamCooperationApplications(String teamId) {
        return findByFromTeamIdOrToTeamIdOrderByCreateTimeDesc(teamId, teamId);
    }
}
