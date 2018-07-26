package com.mj.ocean.surcharge.service.impl;

import com.mj.core.data.properties.StatusProperties;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.carrier.model.Carrier;
import com.mj.general.port.model.Port;
import com.mj.ocean.basic.service.OceanGeneralService;
import com.mj.ocean.surcharge.dto.SurchargeAddDTO;
import com.mj.ocean.surcharge.dto.SurchargeQueryDTO;
import com.mj.ocean.surcharge.dto.SurchargeUpdateDTO;
import com.mj.ocean.surcharge.model.QSurcharge;
import com.mj.ocean.surcharge.model.Surcharge;
import com.mj.ocean.surcharge.repo.SurchargeRepository;
import com.mj.ocean.surcharge.service.SurchargeService;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/18
 */
@Service
public class SurchargeServiceImpl extends SimpleBasicServiceImpl<Surcharge, Integer, SurchargeRepository> implements SurchargeService {
    private final SurchargeRepository surchargeRepository;
    private final OceanGeneralService oceanGeneralService;
    private final StatusProperties status;

    public SurchargeServiceImpl(SurchargeRepository surchargeRepository,
                                OceanGeneralService oceanGeneralService,
                                StatusProperties status) {
        this.surchargeRepository = surchargeRepository;
        this.oceanGeneralService = oceanGeneralService;
        this.status = status;
    }

    @Override
    public Page<Surcharge> find(String costType,SurchargeQueryDTO surchargeQueryDTO, Pageable pageable) {
        QSurcharge surcharge = QSurcharge.surcharge;
        BooleanExpression expression = Expressions.ONE.eq(1);
        expression = expression.and(surcharge.costType.eq(costType));
        if (surchargeQueryDTO.getCarrierId() != null) {
            expression = expression.and(surcharge.carrierId.eq(surchargeQueryDTO.getCarrierId()));
        }
        if (surchargeQueryDTO.getPodId() != null) {
            expression = expression.and(surcharge.podId.eq(surchargeQueryDTO.getPodId()));
        }
        if (surchargeQueryDTO.getPomId() != null) {
            expression = expression.and(surcharge.pomId.eq(surchargeQueryDTO.getPomId()));
        }
        return surchargeRepository.findAll(expression, pageable);
    }

    @Override
    public void toggleEnable(Integer id) {
        Surcharge surcharge = this.get(id);
        if (status.getEnable().equals(surcharge.getEnabled())) {
            surcharge.setEnabled(status.getDisable());
        } else {
            surcharge.setEnabled(status.getEnable());
        }
        this.update(surcharge);
    }

    @Override
    public void update(SurchargeUpdateDTO surchargeUpdateDTO) {
        //修改时 1.先删除之前的
        surchargeRepository.deleteByCarrierIdAndPodIdAndPomIdAndCostType(surchargeUpdateDTO.getCarrierId(),
                surchargeUpdateDTO.getPodId(), surchargeUpdateDTO.getPomId(),surchargeUpdateDTO.getCostType());
        //  再添加修改后的
        Port pod = oceanGeneralService.getPort(surchargeUpdateDTO.getPodId());
        Port pom = oceanGeneralService.getPort(surchargeUpdateDTO.getPomId());
        Carrier carrier = oceanGeneralService.getCarrier(surchargeUpdateDTO.getCarrierId());
        surchargeUpdateDTO.getSurcharges().forEach(keeper -> {
            Surcharge surcharge = new Surcharge();
            surcharge.setCostType(surchargeUpdateDTO.getCostType());
            surcharge.setCarrierId(surchargeUpdateDTO.getCarrierId());
            surcharge.setCarrierName(carrier.getCarrierEN());
            surcharge.setPodId(surchargeUpdateDTO.getPodId());
            surcharge.setPodName(pod.getPortEN());
            surcharge.setPomId(surchargeUpdateDTO.getPomId());
            surcharge.setPomName(pom.getPortEN());
            surcharge.setCode(keeper.getCode());
            surcharge.setCurrency(keeper.getCurrency());
            surcharge.setEnabled(keeper.getEnabled());
            surcharge.setNameCn(keeper.getNameCn());
            surcharge.setNameEn(keeper.getNameEn());
            surcharge.setRemarks(keeper.getRemarks());
            surcharge.setPayWay(keeper.getPayWay());
            surcharge.setAmt(keeper.getAmt());
            surcharge.setBillingUnit(keeper.getBillingUnit());
            this.add(surcharge);
        });
    }

    @Override
    public void add(SurchargeAddDTO surchargeAddDTO) {
        Port pod = oceanGeneralService.getPort(surchargeAddDTO.getPodId());
        Port pom = oceanGeneralService.getPort(surchargeAddDTO.getPomId());
        Carrier carrier = oceanGeneralService.getCarrier(surchargeAddDTO.getCarrierId());
        surchargeAddDTO.getSurcharges().forEach(keeper -> {
            Surcharge surcharge = new Surcharge();
            surcharge.setCostType(surchargeAddDTO.getCostType());
            surcharge.setCarrierId(surchargeAddDTO.getCarrierId());
            surcharge.setCarrierName(carrier.getCarrierEN());
            surcharge.setPodId(surchargeAddDTO.getPodId());
            surcharge.setPodName(pod.getPortEN());
            surcharge.setPomId(surchargeAddDTO.getPomId());
            surcharge.setPomName(pom.getPortEN());
            surcharge.setCode(keeper.getCode());
            surcharge.setCurrency(keeper.getCurrency());
            surcharge.setEnabled(keeper.getEnabled());
            surcharge.setNameCn(keeper.getNameCn());
            surcharge.setNameEn(keeper.getNameEn());
            surcharge.setRemarks(keeper.getRemarks());
            surcharge.setPayWay(keeper.getPayWay());
            surcharge.setAmt(keeper.getAmt());
            surcharge.setBillingUnit(keeper.getBillingUnit());
            this.add(surcharge);
        });
    }

    @Override
    public List<Surcharge> findSameGroup(Integer carrierId, Integer pomId, Integer podId,String costType) {
        return surchargeRepository.findByCarrierIdAndPomIdAndPodIdAndCostType(carrierId, pomId, podId,costType);
    }
}
