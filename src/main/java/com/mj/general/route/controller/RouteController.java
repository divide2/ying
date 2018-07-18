package com.mj.general.route.controller;

import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.general.route.dto.RouteAddDTO;
import com.mj.general.route.dto.RouteEnabledDTO;
import com.mj.general.route.dto.RouteQueryDTO;
import com.mj.general.route.dto.RouteUpdateDTO;
import com.mj.general.route.model.Route;
import com.mj.general.route.service.RouteService;
import com.mj.general.route.vo.RouteVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zejun
 * @date 2018/7/10 11:33
 */
@RestController
@RequestMapping("/v1/route")
@Api(tags = "航线管理")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    @ApiOperation("新增航线")
    public ResponseEntity<Messager> add(@Valid @RequestBody RouteAddDTO routeAddDTO, BindingResult br) {
        routeService.addRouteAndPort(routeAddDTO);
        return Responser.created();
    }

    @PatchMapping("/stats")
    @ApiOperation("禁用状态")
    public ResponseEntity<Messager> enabled(@Valid @RequestBody RouteEnabledDTO routeEnabledDTO, BindingResult br){
        Route route = routeService.get(routeEnabledDTO.getId());
        route.setStatus(routeEnabledDTO.getStatus());
        routeService.update(route);
        return  Responser.updated();
    }

    @PatchMapping
    @ApiOperation("修改航线")
    public ResponseEntity<Messager> update(@Valid @RequestBody RouteUpdateDTO routeUpdateDTO,BindingResult br){
        routeService.updateRouteAndPort(routeUpdateDTO);
        return Responser.updated();
    }

    @GetMapping("/{id}")
    @ApiOperation("查看单条信息")
    public ResponseEntity<RouteVO> get(@PathVariable Integer id) {
        RouteVO routeVO = routeService.getDetail(id);
        return ResponseEntity.ok(routeVO);
    }

    @GetMapping("/find")
    @ApiOperation("航线管理分页查询")
    public ResponseEntity<Page<RouteVO>> find(RouteQueryDTO routeQueryDTO, Pageable pageable) {
        Page<Route> routes = routeService.find(routeQueryDTO,pageable);
        Page<RouteVO> page = routes.map(route -> RouteVO.builder()
                .id(route.getId())
                .carrierId(route.getCarrierId())
                .carrierEN(route.getCarrierEN())
                .carrierCode(route.getCarrierCode())
                .routeCode(route.getRouteCode())
                .num(route.getNum())
                .firstPort(route.getFirstPort())
                .lastPort(route.getLastPort())
                .allTime(route.getAllTime())
                .status(route.getStatus()).build());
        return ResponseEntity.ok(page);
    }
}
