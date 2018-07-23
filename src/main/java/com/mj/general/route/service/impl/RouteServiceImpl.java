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
import com.mj.general.route.vo.RouteCarrierVO;
import com.mj.general.route.vo.RoutePortVO;
import com.mj.general.route.vo.RouteVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private List<Integer> getPortsIds(List<RoutePortAddOrUpdateDTO> routePorts) {
        return routePorts.stream().map(RoutePortAddOrUpdateDTO::getPortId).collect(Collectors.toList());
    }

    private Port getPort(Integer id) {
        return portRepository.getOne(id);
    }

    private void addRoutePort(int routeId, List<RoutePortAddOrUpdateDTO> routePortAddOrUpdateDTOS) {
        for (RoutePortAddOrUpdateDTO routePortAddOrUpdateDTO : routePortAddOrUpdateDTOS) {
            Port port = this.getPort(routePortAddOrUpdateDTO.getPortId());
            RoutePort routePort = RoutePort.builder().portId(routePortAddOrUpdateDTO.getPortId())
                    .portEN(port.getPortEN())
                    .countryEN(port.getCountryEN())
                    .etc(routePortAddOrUpdateDTO.getEtc())
                    .etd(routePortAddOrUpdateDTO.getEtd())
                    .tt(routePortAddOrUpdateDTO.getTt())
                    .pier(routePortAddOrUpdateDTO.getPier())
                    .orderNum(routePortAddOrUpdateDTO.getOrderNum())
                    .routeId(routeId).build();
            routePortService.add(routePort);
        }
    }

    private Integer addRoute(RouteAddDTO routeAddDTO, Carrier carrier, Port firstPort,
                             Port lastPort, BigDecimal allTime, String allPortStr, int num) {
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
        return route.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRouteAndPort(RouteAddDTO routeAddDTO) {
        List<RoutePortAddOrUpdateDTO> routePortAddOrUpdateDTOS = routeAddDTO.getRoutePorts();
        int num = routePortAddOrUpdateDTOS.size();
        List<Port> allPorts = portRepository.findByIdIn(getPortsIds(routePortAddOrUpdateDTOS));
        Port firstPort = allPorts.get(0);
        Port lastPort = allPorts.get(num - 1);
        BigDecimal allTime = routePortAddOrUpdateDTOS.stream().map(RoutePortAddOrUpdateDTO::getTt).reduce(BigDecimal::add).get();
        String allPortStr = allPorts.stream().map(Port::getPortEN).collect(Collectors.joining(Punctuation.COMMA));
        Carrier carrier = carrierRepository.getOne(routeAddDTO.getCarrierId());
        int routeId = addRoute(routeAddDTO, carrier, firstPort, lastPort, allTime, allPortStr, num);
        addRoutePort(routeId, routePortAddOrUpdateDTOS);
    }

    private void updateRoute(RouteUpdateDTO routeUpdateDTO, Carrier carrier, int num,
                             Port firstPort, Port lastPort, BigDecimal allTime, String allPortStr) {
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
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRouteAndPort(RouteUpdateDTO routeUpdateDTO) {
        List<RoutePortAddOrUpdateDTO> routePortAddOrUpdateDTOS = routeUpdateDTO.getRoutePorts();
        int num = routePortAddOrUpdateDTOS.size();
        List<Port> allPorts = portRepository.findByIdIn(getPortsIds(routePortAddOrUpdateDTOS));
        Port firstPort = allPorts.get(0);
        Port lastPort = allPorts.get(num - 1);
        BigDecimal allTime = routePortAddOrUpdateDTOS.stream().map(RoutePortAddOrUpdateDTO::getTt).reduce(BigDecimal::add).get();
        String allPortStr = allPorts.stream().map(Port::getPortEN).collect(Collectors.joining(Punctuation.COMMA));
        Carrier carrier = carrierRepository.getOne(routeUpdateDTO.getCarrierId());

        updateRoute(routeUpdateDTO, carrier, num, firstPort, lastPort, allTime, allPortStr);
        //修改时，先删除航线下的之前的港口，再添加
        routePortRepository.deleteByRouteId(routeUpdateDTO.getId());
        int routeId = routeUpdateDTO.getId();
        addRoutePort(routeId, routePortAddOrUpdateDTOS);
    }

    private RouteVO routeToVO(Integer id, Route route, List<RoutePortVO> vos) {
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

    private List<RoutePortVO> routePortVOs(List<RoutePort> routePortList) {
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
        return vos;
    }

    @Override
    public RouteVO getDetail(Integer id) {
        Route route = get(id);
        List<RoutePort> routePortList = routePortRepository.findByRouteId(id);
        List<RoutePortVO> vos = routePortVOs(routePortList);
        return routeToVO(id, route, vos);
    }

    @Override
    public Page<Route> find(RouteQueryDTO routeQueryDTO, Pageable pageable) {
        QRoute route = QRoute.route;
        BooleanExpression predicate = Expressions.ONE.eq(Expressions.ONE);
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
            Route exitCode = routeRepository.getByRouteCodeIgnoreCase(routeCheckDTO.getRouteCode());
            if (exitCode != null) {
                throw new AlreadyExistsException();
            }
        }
    }


    @Override
    public RouteCarrierVO getByCarrierId(Integer carrierId) {
        Route route = routeRepository.getByCarrierId(carrierId);
        RouteCarrierVO routeCarrierVO = RouteCarrierVO.builder()
                .id(route.getId())
                .carrierId(route.getCarrierId())
                .routeCode(route.getRouteCode()).build();
        return routeCarrierVO;
    }

    @Override
    public List<RoutePortVO> findByRouteId(Integer routeId) {
        List<RoutePort> routePortList = routePortRepository.getByRouteIdOrderByOrderNum(routeId);
        List<RoutePortVO> vos = routePortList.stream().map(routePort -> RoutePortVO.builder().id(routePort.getId())
                .portId(routePort.getPortId())
                .portEN(routePort.getPortEN())
                .etc(routePort.getEtc())
                .etd(routePort.getEtd())
                .orderNum(routePort.getOrderNum())
                .tt(routePort.getTt())
                .routeId(routePort.getRouteId()).build()).collect(Collectors.toList());
        return vos;
    }
}
