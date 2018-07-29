package com.mj.ocean.surcharge.service.impl;

import com.mj.core.data.properties.CostTypeProperties;
import com.mj.core.data.properties.StatusProperties;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.carrier.model.Carrier;
import com.mj.general.port.model.Port;
import com.mj.ocean.basic.service.OceanGeneralService;
import com.mj.ocean.portcombination.model.PortCombination;
import com.mj.ocean.portcombination.repo.PortCombinationRepository;
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
import org.springframework.transaction.annotation.Transactional;

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
    private final CostTypeProperties costTypeProperties;
    private final PortCombinationRepository portCombinationRepository;

    public SurchargeServiceImpl(SurchargeRepository surchargeRepository,
                                OceanGeneralService oceanGeneralService,
                                StatusProperties status,
                                CostTypeProperties costTypeProperties,
                                PortCombinationRepository portCombinationRepository) {
        this.surchargeRepository = surchargeRepository;
        this.oceanGeneralService = oceanGeneralService;
        this.status = status;
        this.costTypeProperties = costTypeProperties;
        this.portCombinationRepository = portCombinationRepository;
    }

    @Override
    public Page<Surcharge> find(String costType, SurchargeQueryDTO surchargeQueryDTO, Pageable pageable) {
        //todo 获取登陆用户的客户公司id
        int companyId = 1;
        QSurcharge surcharge = QSurcharge.surcharge;
        BooleanExpression expression = Expressions.ONE.eq(1);
        expression = expression.and(surcharge.costType.eq(costType));
        expression = expression.and(surcharge.companyId.eq(companyId));
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
    @Transactional(rollbackFor = Exception.class)
    public void update(SurchargeUpdateDTO surchargeUpdateDTO) {
        //todo 获取登陆用户的客户公司id
        int companyId = 1;
        //修改时 1.先删除之前的
        surchargeRepository.deleteByCarrierIdAndPodIdAndPomIdAndCostType(surchargeUpdateDTO.getCarrierId(),
                surchargeUpdateDTO.getPodId(), surchargeUpdateDTO.getPomId(), surchargeUpdateDTO.getCostType());
        //  再添加修改后的
        SingleOrCombinationPort podSoc = this.getSingleOrCombinationPort(surchargeUpdateDTO.getPodId(), surchargeUpdateDTO.getCostType());
        SingleOrCombinationPort pomSoc = this.getSingleOrCombinationPort(surchargeUpdateDTO.getPomId(), surchargeUpdateDTO.getCostType());
        Carrier carrier = oceanGeneralService.getCarrier(surchargeUpdateDTO.getCarrierId());
        surchargeUpdateDTO.getSurcharges().forEach(keeper -> {
            Surcharge surcharge = new Surcharge();
            surcharge.setCostType(surchargeUpdateDTO.getCostType());
            surcharge.setCarrierId(surchargeUpdateDTO.getCarrierId());
            surcharge.setCarrierName(carrier.getCarrierEN());
            surcharge.setPodId(podSoc.getPortId());
            surcharge.setPodName(podSoc.getPortEN());
            surcharge.setPomId(pomSoc.getPortId());
            surcharge.setPomName(pomSoc.getPortEN());
            surcharge.setCode(keeper.getCode());
            surcharge.setCurrency(keeper.getCurrency());
            surcharge.setEnabled(keeper.getEnabled());
            surcharge.setNameCn(keeper.getNameCn());
            surcharge.setNameEn(keeper.getNameEn());
            surcharge.setRemarks(keeper.getRemarks());
            surcharge.setPayWay(keeper.getPayWay());
            surcharge.setAmt(keeper.getAmt());
            surcharge.setBillingUnit(keeper.getBillingUnit());
            surcharge.setCompanyId(companyId);
            this.add(surcharge);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SurchargeAddDTO surchargeAddDTO) {
        //todo 获取登陆用户的客户公司id
        int companyId = 1;
        SingleOrCombinationPort podSoc = this.getSingleOrCombinationPort(surchargeAddDTO.getPodId(), surchargeAddDTO.getCostType());
        SingleOrCombinationPort pomSoc = this.getSingleOrCombinationPort(surchargeAddDTO.getPomId(), surchargeAddDTO.getCostType());
        Carrier carrier = oceanGeneralService.getCarrier(surchargeAddDTO.getCarrierId());
        surchargeAddDTO.getSurcharges().forEach(keeper -> {
            Surcharge surcharge = new Surcharge();
            surcharge.setCompanyId(companyId);
            surcharge.setCostType(surchargeAddDTO.getCostType());
            surcharge.setCarrierId(surchargeAddDTO.getCarrierId());
            surcharge.setCarrierName(carrier.getCarrierEN());
            surcharge.setPodId(podSoc.getPortId());
            surcharge.setPodName(podSoc.getPortEN());
            surcharge.setPomId(podSoc.getPortId());
            surcharge.setPomName(pomSoc.getPortEN());
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
    public List<Surcharge> findSameGroup(Integer carrierId, Integer pomId, Integer podId, String costType) {
        //todo 获取登陆用户的客户公司id
        int companyId = 1;
        return surchargeRepository.findByCarrierIdAndPomIdAndPodIdAndCostTypeAndCompanyId(
                carrierId, pomId, podId, costType, companyId);
    }

    /**
     * 获取组合或者是单个港口
     *
     * @param portId   港口/组合id
     * @param costType 类型 G or S
     * @return 单个/组合港口信息
     */
    private SingleOrCombinationPort getSingleOrCombinationPort(Integer portId, String costType) {
        // 常规组合费用
        SingleOrCombinationPort soc = new SingleOrCombinationPort();
        if (costTypeProperties.getSpecial().equals(costType)) {
            Port port = oceanGeneralService.getPort(portId);
            soc.setPortId(port.getId());
            soc.setPortEN(port.getPortEN());
        }
        // 特殊单个费用
        else {
            PortCombination pc = portCombinationRepository.getOne(portId);
            soc.setPortId(pc.getId());
            soc.setPortEN(pc.getCombinationName());
        }
        return soc;
    }
}
