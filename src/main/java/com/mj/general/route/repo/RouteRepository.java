package com.mj.general.route.repo;

import com.mj.general.route.model.Route;
import com.mj.general.route.model.RoutePort;
import com.mj.general.route.vo.RouteCarrierVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/10 11:28
 */
@Repository
public interface RouteRepository extends JpaRepository<Route,Integer>, QuerydslPredicateExecutor<Route> {

    /**
     * 根据航线代码查询数据
     * @param routeCode 航线代码
     * @return Route
     */
    Route getByRouteCode(String routeCode);

    Route getByCarrierId(Integer carrierId);


}
