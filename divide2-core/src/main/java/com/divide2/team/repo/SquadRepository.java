package com.divide2.team.repo;

import com.divide2.team.model.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * @author bvvy
 */
public interface SquadRepository extends JpaRepository<Squad, String>, QuerydslPredicateExecutor<Squad> {

    List<Squad> findByTeamId(String teamId);
}
