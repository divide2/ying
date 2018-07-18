package com.mj.general.route.service.impl;

import com.mj.core.exception.AlreadyExistsException;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.core.val.Punctuation;
import com.mj.general.carrier.model.Carrier;
import com.mj.general.carrier.repo.CarrierRepository;
import com.mj.general.port.model.Port;
import com.mj.general.port.repo.PortRepository;
import com.mj.general.route.dto.*;
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
 * @author zejun
 * @date 2018/7/10 11:27
 */
@Service
public class RouteServiceImpl extends SimpleBasicServiceImpl<Route, Integer, RouteRepository> implements RouteService {
    private final RouteRepository routeRepository;
    private final RoutePortService routePortService;
    private final RoutePortRepository routePortRepository;
    private final CarrierRepository carrierRepository;
    private final PortRepository portRepository;

    public RouteServiceImpl(RouteRepository routeRepository,
                            RoutePortService routePortService,
                            RoutePortRepository routePortRepository,
                            CarrierRepository carrierRepository,
                            PortRepository portRepository) {
        this.routeRepository = routeRepository;
        this.routePortService = routePortService;
        this.routePortRepository = routePortRepository;
        this.carrierRepository = carrierRepository;
        this.portRepository = portRepository;
    }

    private List<Integer> getPortsIds(List<RoutePortAddDTO> routePorts) {
        return routePorts.stream().map(RoutePortAddDTO::getPortId).collect(Collectors.toList());
    }

    private Port getPort(Integer id) {
        return portRepository.getOne(id);
    }

    @Override
    public void addRouteAndPort(RouteAddDTO routeAddDTO) {
        List<RoutePortAddDTO> routePortAddDTOS = routeAddDTO.getRoutePorts();
        int num = routePortAddDTOS.size();
        List<Port> allPorts = portRepository.findByIdIn(getPortsIds(routePortAddDTOS));
        Port firstPort = allPorts.get(0);
        Port lastPort = allPorts.get(num - 1);
        BigDecimal allTime  = routePortAddDTOS.stream().map(RoutePortAddDTO::getTt).reduce(BigDecimal::add).get();
        String allPortStr = allPorts.stream().map(Port::getPortEN).collect(Collectors.joining(Punctuation.COMMA));
        Carrier carrier = carrierRepository.getOne(routeAddDTO.getCarrierId());
        Route route = Route.builder().carrierId(routeAddDTO.getCarrierId())
                .carrierCode(carrier.getCarrierCode())
                .carrierEN(carrier.getCarrierEN())
                .routeCode(routeAddDTO.getRouteCode())
                .routeFullName(routeAddDTO.getRouteFullName())
                .routeDesc(routeAddDTO.getRouteDesc())
                .routeMapUrl(routeAddDTO.getRouteMapUrl())
                .num(num)
                .firstPort(firstPort.getPortEN())
                .lastPort(lastPort.getPortEN())
                .allPort(allPortStr)
                .allTime(allTime).build();
        this.add(route);
        int routeId = route.getId();
        for (RoutePortAddDTO routePortAddDTO : routePortAddDTOS) {
            Port port = this.getPort(routePortAddDTO.getPortId());
            RoutePort routePort = RoutePort.builder().portId(routePortAddDTO.getPortId())
                    .portEN(port.getPortEN())
                    .countryEN(port.getCountryEN())
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
        List<RoutePortAddDTO> routePortAddDTOS = routeUpdateDTO.getRoutePorts();
        int num = routePortAddDTOS.size();
        List<Port> allPorts = portRepository.findByIdIn(getPortsIds(routePortAddDTOS));
        Port firstPort = allPorts.get(0);
        Port lastPort = allPorts.get(num - 1);
        BigDecimal allTime  = routePortAddDTOS.stream().map(RoutePortAddDTO::getTt).reduce(BigDecimal::add).get();
        String allPortStr = allPorts.stream().map(Port::getPortEN).collect(Collectors.joining(Punctuation.COMMA));
        Carrier carrier = carrierRepository.getOne(routeUpdateDTO.getCarrierId());
        Route route = this.get(routeUpdateDTO.getId());
        route.setCarrierId(routeUpdateDTO.getCarrierId());
        route.setCarrierCode(carrier.getCarrierCode());
        route.setCarrierEN(carrier.getCarrierEN());
        route.setRouteCode(routeUpdateDTO.getRouteCode());
        route.setRouteFullName(routeUpdateDTO.getRouteFullName());
        route.setRouteDesc(routeUpdateDTO.getRouteDesc());
        route.setRouteMapUrl(routeUpdateDTO.getRouteMapUrl());
        route.setNum(num);
        route.setAllPort(allPortStr);
        route.setFirstPort(firstPort.getPortEN());
        route.setLastPort(lastPort.getPortEN());
        route.setAllTime(allTime);
        update(route);
        //修改时，先删除航线下的之前的港口，再添加
        routePortRepository.deleteByRouteId(route.getId());

        int routeId = route.getId();
        for (RoutePortAddDTO routePortAddDTO : routePortAddDTOS) {
            Port port = this.getPort(routePortAddDTO.getPortId());
            RoutePort routePort1 = RoutePort.builder().portId(routePortAddDTO.getPortId())
                    .portEN(port.getPortEN())
                    .countryEN(port.getCountryEN())
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
        RouteVO vo = RouteVO.builder().routePorts(vos)
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
        predicate.and(route.status.eq("1"));
        if (StringUtils.isNotEmpty(routeQueryDTO.getCarrierEN())) {
            predicate = route.carrierEN.like("%" + routeQueryDTO.getCarrierEN() + "%");
        }
        if (StringUtils.isNotEmpty(routeQueryDTO.getRouteCode())) {
            predicate = route.routeCode.like("%" + routeQueryDTO.getRouteCode() + "%");
        }
        if (StringUtils.isNotEmpty(routeQueryDTO.getAllPort())) {
            predicate = route.allPort.like("%" + routeQueryDTO.getAllPort() + "%");
        }
        return routeRepository.findAll(predicate, pageable);
    }

    @Override
    public void check(RouteCheckDTO routeCheckDTO) {
        if (StringUtils.isNotEmpty(routeCheckDTO.getRouteCode())) {
            Route exitCode = routeRepository.getByRouteCode(routeCheckDTO.getRouteCode());
            if (exitCode != null) {
                throw new AlreadyExistsException();
            }
        }
    }
}
