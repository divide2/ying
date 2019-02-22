package com.ying.team.repo;

import com.ying.team.model.TeamCooperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/20
 */
public interface TeamCooperationRepository extends JpaRepository<TeamCooperation, String> {

    TeamCooperation getByFromTeamIdAndToTeamId(String fromTeamId, String toTeamId);

    /**
     * 企业的合作企业
     * @param teamId teamId
     * @return groups
     */
    List<TeamCooperation> findByFromTeamId(String teamId);
}
