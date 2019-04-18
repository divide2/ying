package com.divide2.team.repo;

import com.divide2.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2019/2/13
 */
public interface TeamRepository extends JpaRepository<Team, String> {

    Team getByName(String name);
}
