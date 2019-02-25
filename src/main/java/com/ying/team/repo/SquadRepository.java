package com.ying.team.repo;

import com.ying.team.model.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author bvvy
 */
public interface SquadRepository extends JpaRepository<Squad, String>, QuerydslPredicateExecutor<Squad> {

}
