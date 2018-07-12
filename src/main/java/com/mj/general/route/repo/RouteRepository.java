package com.mj.general.route.repo;

import com.mj.general.route.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zejun
 * @date 2018/7/10 11:28
 */
@Repository
public interface RouteRepository extends JpaRepository<Route,Integer>, QuerydslPredicateExecutor<Route> {

    Route getByRouteCode(String routeCode);
}
