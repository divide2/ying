package com.mj.general.route.repo;

import com.mj.general.port.model.Port;
import com.mj.general.route.model.Route;
import com.mj.general.route.model.RoutePort;
import com.mj.general.route.vo.RouteCarrierVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Route getByRouteCodeIgnoreCase(String routeCode);

    /**
     * 根据船id查询数据
     * @param carrierId 船id
     * @return Route
     */
    Route getByCarrierId(Integer carrierId);

    /**
     * 根据状态排序分页查询
     * @param predicate  sql数据
     * @param pageable 页面数据
     * @return Page<Route>
     */
    Page<Route> findAllByOrderByEnabledDesc(BooleanExpression predicate, Pageable pageable);
}
