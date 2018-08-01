package com.mj.general.dictionary.repo;

import com.mj.general.dictionary.model.GeneralRouteJointName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zejun
 * @date 2018/7/27 18:33
 */
@Repository
public interface RouteNameRepository extends JpaRepository<GeneralRouteJointName,Integer>,QuerydslPredicateExecutor<GeneralRouteJointName> {
}
