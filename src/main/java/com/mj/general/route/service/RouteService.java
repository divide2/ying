package com.mj.general.route.service;

import com.mj.core.service.BasicService;
import com.mj.general.route.dto.RouteAddDTO;
import com.mj.general.route.dto.RouteCheckDTO;
import com.mj.general.route.dto.RouteQueryDTO;
import com.mj.general.route.dto.RouteUpdateDTO;
import com.mj.general.route.model.Route;
import com.mj.general.route.vo.RouteCarrierVO;
import com.mj.general.route.vo.RoutePortVO;
import com.mj.general.route.vo.RouteVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/10 11:25
 */
public interface RouteService extends BasicService<Route,Integer> {

    /**
     * 新增航线
     * @param routeAddDTO 新增航线dto
     */
    void addRouteAndPort(RouteAddDTO routeAddDTO);

    /**
     * 修改航线
     * @param routeUpdateDTO 修改航线dto
     */
    void updateRouteAndPort(RouteUpdateDTO routeUpdateDTO);

    /**
     * 查看单条信息
     * @param id 航线id
     * @return RouteVO
     */
    RouteVO getDetail(Integer id);

    /**
     * 分页查询
     * @param routeQueryDTO 分页查询dto
     * @param pageable 分页数据
     * @return Page<Route>
     */
    Page<Route> find(RouteQueryDTO routeQueryDTO,Pageable pageable);

    /**
     * 检查字段是否重复
     * @param routeCheckDTO  检查字段dto
     */
    void check(RouteCheckDTO routeCheckDTO);

    RouteCarrierVO getByCarrierId(Integer carrierId);

    List<RoutePortVO> findByRouteId(Integer routeId);
}
