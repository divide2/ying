package com.divide2.team.repo;

import com.divide2.team.model.TeamJoinApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/17
 */
public interface TeamApplicationRepository extends JpaRepository<TeamJoinApplication, String> {

    TeamJoinApplication getByFromIdAndToTeamId(Integer userId, String toTeamId);

    /**
     * 查某个团队下的申请
     * @param teamId  teamId
     * @return application
     */
    List<TeamJoinApplication> findByToTeamIdOrderByUpdateTimeDesc(String teamId);


}
