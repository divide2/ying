package com.ying.team.repo;

import com.ying.team.model.Squad;
import com.ying.team.vo.SquadVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * @author bvvy
 */
public interface SquadRepository extends JpaRepository<Squad, String>, QuerydslPredicateExecutor<Squad> {

    List<Squad> findByTeamId(String teamId);
}
