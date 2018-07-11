package com.mj.general.route.repo;

import com.mj.general.route.model.RoutePort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/10 15:19
 */
@Repository
public interface RoutePortRepository extends JpaRepository<RoutePort,Integer>,QuerydslPredicateExecutor<RoutePort> {


     List<RoutePort> findByRouteId(Integer routeId);
}
