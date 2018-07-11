package com.mj.general.route.service.impl;

import com.mj.core.exception.GeneralException;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.route.dto.RouteAddDTO;
import com.mj.general.route.dto.RoutePortAddDTO;
import com.mj.general.route.dto.RouteQueryDTO;
import com.mj.general.route.dto.RouteUpdateDTO;
import com.mj.general.route.model.QRoute;
import com.mj.general.route.model.Route;
import com.mj.general.route.model.RoutePort;
import com.mj.general.route.repo.RoutePortRepository;
import com.mj.general.route.repo.RouteRepository;
import com.mj.general.route.service.RoutePortService;
import com.mj.general.route.service.RouteService;
import com.mj.general.route.vo.RoutePortVO;
import com.mj.general.route.vo.RouteVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther: zejun
 * @date: 2018/7/10 11:27
 */
@Service
public class RouteServiceImpl extends SimpleBasicServiceImpl<Route, Integer, RouteRepository> implements RouteService {
    private final RouteRepository routeRepository;
    private final RoutePortService routePortService;
    private RoutePortRepository routePortRepository;

    public RouteServiceImpl(RouteRepository routeRepository, RoutePortService routePortService, RoutePortRepository routePortRepository) {
        this.routeRepository = routeRepository;
        this.routePortService = routePortService;
        this.routePortRepository = routePortRepository;
    }

    @Override
    public void addRouteAndPort(RouteAddDTO routeAddDTO) throws GeneralException {
        List<RoutePortAddDTO> routePortAddDTOS = routeAddDTO.getRoutePortAddDTOList();
        int num = routePortAddDTOS.size();
        String firstPort = routePortAddDTOS.get(0).getPortEN();
        String lastPort = routePortAddDTOS.get(num-1).getPortEN();
        BigDecimal allTime = new BigDecimal("0.0");
        String allPort = "";
        for (int i = 0; i < num; i++) {
            allTime = allTime.add(routePortAddDTOS.get(i).getTt());
            allPort += routePortAddDTOS.get(i).getPortEN()+",";
        }

        Route route = Route.builder().carrierId(routeAddDTO.getCarrierId())
                .carrierCode(routeAddDTO.getCarrierCode())
                .carrierEN(routeAddDTO.getCarrierEN())
                .routeCode(routeAddDTO.getRouteCode())
                .routeFullName(routeAddDTO.getRouteFullName())
                .routeDesc(routeAddDTO.getRouteDesc())
                .routeMapUrl(routeAddDTO.getRouteMapUrl())
                .num(num)
                .firstPort(firstPort)
                .lastPort(lastPort)
                .allPort(allPort)
                .allTime(allTime).build();
        add(route);

        int routeId = route.getId();
        for (RoutePortAddDTO routePortAddDTO : routePortAddDTOS) {
            RoutePort routePort = RoutePort.builder().portId(routePortAddDTO.getPortId())
                    .portEN(routePortAddDTO.getPortEN())
                    .countryEN(routePortAddDTO.getCountryEN())
                    .etc(routePortAddDTO.getEtc())
                    .etd(routePortAddDTO.getEtd())
                    .tt(routePortAddDTO.getTt())
                    .pier(routePortAddDTO.getPier())
                    .orderNum(routePortAddDTO.getOrderNum())
                    .routeId(routeId).build();
            routePortService.add(routePort);
        }
    }

    @Override
    public void updateRouteAndPort(RouteUpdateDTO routeUpdateDTO) {
        List<RoutePortAddDTO> routePortAddDTOS = routeUpdateDTO.getRoutePortAddDTOList();
        int num = routePortAddDTOS.size();
        String firstPort = routePortAddDTOS.get(0).getPortEN();
        String lastPort = routePortAddDTOS.get(num-1).getPortEN();
        BigDecimal allTime = new BigDecimal("0.0");
        String allPort = "";
        for (int i = 0; i < num; i++) {
            allTime = allTime.add(routePortAddDTOS.get(i).getTt());
            allPort += routePortAddDTOS.get(i).getPortEN()+",";
        }

        Route route = get(routeUpdateDTO.getId());
        route.setCarrierId(routeUpdateDTO.getCarrierId());
        route.setCarrierCode(routeUpdateDTO.getCarrierCode());
        route.setCarrierEN(routeUpdateDTO.getCarrierEN());
        route.setRouteCode(routeUpdateDTO.getRouteCode());
        route.setRouteFullName(routeUpdateDTO.getRouteFullName());
        route.setRouteDesc(routeUpdateDTO.getRouteDesc());
        route.setRouteMapUrl(routeUpdateDTO.getRouteMapUrl());
        route.setNum(num);
        route.setAllPort(allPort);
        route.setFirstPort(firstPort);
        route.setLastPort(lastPort);
        route.setAllTime(allTime);
        update(route);

        //修改时，先删除航线下的之前的港口，再添加
        RoutePort routePort = new RoutePort();
        routePort.setPortId(route.getId());
        routePortRepository.delete(routePort);

        int routeId = route.getId();
        for (RoutePortAddDTO routePortAddDTO : routePortAddDTOS) {
            RoutePort routePort1 = RoutePort.builder().portId(routePortAddDTO.getPortId())
                    .portEN(routePortAddDTO.getPortEN())
                    .countryEN(routePortAddDTO.getCountryEN())
                    .etc(routePortAddDTO.getEtc())
                    .etd(routePortAddDTO.getEtd())
                    .tt(routePortAddDTO.getTt())
                    .pier(routePortAddDTO.getPier())
                    .orderNum(routePortAddDTO.getOrderNum())
                    .routeId(routeId).build();
            routePortService.add(routePort1);
        }
    }

    @Override
    public RouteVO getDetail(Integer id) {
        Route route = get(id);
        List<RoutePort> routePortList = routePortRepository.findByRouteId(id);
        List<RoutePortVO> vos = routePortList.stream().map(
                it -> RoutePortVO.builder().id(it.getId())
                        .portId(it.getPortId())
                        .portEN(it.getPortEN())
                        .countryEN(it.getCountryEN())
                        .etc(it.getEtc())
                        .etd(it.getEtd())
                        .tt(it.getTt())
                        .pier(it.getPier())
                        .orderNum(it.getOrderNum())
                        .routeId(it.getRouteId()).build()

        ).collect(Collectors.toList());
        RouteVO vo = RouteVO.builder().routePortVOList(vos)
                .id(id)
                .carrierId(route.getCarrierId())
                .carrierEN(route.getCarrierEN())
                .carrierCode(route.getCarrierCode())
                .routeCode(route.getRouteCode())
                .routeFullName(route.getRouteFullName())
                .routeDesc(route.getRouteDesc())
                .routeMapUrl(route.getRouteMapUrl())
                .num(route.getNum())
                .firstPort(route.getFirstPort())
                .lastPort(route.getLastPort())
                .allTime(route.getAllTime()).build();
        return vo;
    }

    @Override
    public Page<Route> find(RouteQueryDTO routeQueryDTO, Pageable pageable) {
        QRoute route = QRoute.route;
        BooleanExpression predicate = route.deleted.eq(0);
        if(StringUtils.isNotEmpty(routeQueryDTO.getCarrierEN())){
            predicate = route.carrierEN.like("%" + routeQueryDTO.getCarrierEN() + "%");
        }
        if(StringUtils.isNotEmpty(routeQueryDTO.getRouteCode())){
            predicate = route.routeCode.like("%" + routeQueryDTO.getRouteCode() + "%");
        }
        if(StringUtils.isNotEmpty(routeQueryDTO.getAllPort())){
            predicate = route.allPort.like("%" + routeQueryDTO.getAllPort() + "%");
        }
        return routeRepository.findAll(predicate,pageable);
    }
}
