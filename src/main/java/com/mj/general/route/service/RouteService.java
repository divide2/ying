package com.mj.general.route.service;

import com.mj.core.service.BasicService;
import com.mj.general.route.dto.RouteAddDTO;
import com.mj.general.route.dto.RouteCheckDTO;
import com.mj.general.route.dto.RouteQueryDTO;
import com.mj.general.route.dto.RouteUpdateDTO;
import com.mj.general.route.model.Route;
import com.mj.general.route.vo.RouteVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @auther: zejun
 * @date: 2018/7/10 11:25
 */
public interface RouteService extends BasicService<Route,Integer> {

    /**
     * 新增航线
     * @param routeAddDTO
     */
    void addRouteAndPort(RouteAddDTO routeAddDTO);

    /**
     * 修改航线
     * @param routeUpdateDTO
     */
    void updateRouteAndPort(RouteUpdateDTO routeUpdateDTO);

    /**
     * 查看单条信息
     * @param id
     * @return
     */
    RouteVO getDetail(Integer id);

    /**
     * 分页查询
     * @param routeQueryDTO
     * @param pageable
     * @return
     */
    Page<Route> find(RouteQueryDTO routeQueryDTO,Pageable pageable);

    void check(RouteCheckDTO routeCheckDTO);


}
