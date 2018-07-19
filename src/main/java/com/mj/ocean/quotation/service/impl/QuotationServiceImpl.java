package com.mj.ocean.quotation.service.impl;

import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.carrier.model.Carrier;
import com.mj.general.carrier.service.CarrierService;
import com.mj.general.port.model.Port;
import com.mj.general.port.service.PortService;
import com.mj.general.route.model.Route;
import com.mj.general.route.service.RouteService;
import com.mj.ocean.costcode.model.CostCode;
import com.mj.ocean.costcode.service.CostCodeService;
import com.mj.ocean.portcombination.model.PortCombinationAssociated;
import com.mj.ocean.portcombination.service.CombinationAssociatedService;
import com.mj.ocean.quotation.dto.*;
import com.mj.ocean.quotation.model.Quotation;
import com.mj.ocean.quotation.model.QuotationCost;
import com.mj.ocean.quotation.repo.QuotationCostRepository;
import com.mj.ocean.quotation.repo.QuotationRepository;
import com.mj.ocean.quotation.repo.custom.QuotationRepositoryCustom;
import com.mj.ocean.quotation.service.QuotationCostService;
import com.mj.ocean.quotation.service.QuotationService;
import com.mj.ocean.quotation.vo.QuotationCostVO;
import com.mj.ocean.quotation.vo.QuotationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zejun
 * @date 2018/7/17 18:25
 */
@Service
public class QuotationServiceImpl extends SimpleBasicServiceImpl<Quotation,Integer,QuotationRepository>
        implements QuotationService {
    private final QuotationRepository quotationRepository;
    private final QuotationCostService quotationCostService;
    private final CarrierService carrierService;
    private final PortService portService;
    private final CombinationAssociatedService combinationAssociatedService;
    private final RouteService routeService;
    private final CostCodeService costCodeService;
    private final QuotationCostRepository quotationCostRepository;

    public QuotationServiceImpl(QuotationRepository quotationRepository,CarrierService carrierService,
                                PortService portService,CombinationAssociatedService combinationAssociatedService,
                                RouteService routeService,CostCodeService costCodeService,
                                QuotationCostService quotationCostService,QuotationCostRepository quotationCostRepository
                                ) {
        this.quotationRepository = quotationRepository;
        this.carrierService = carrierService;
        this.portService = portService;
        this.combinationAssociatedService = combinationAssociatedService;
        this.routeService = routeService;
        this.costCodeService = costCodeService;
        this.quotationCostService = quotationCostService;
        this.quotationCostRepository = quotationCostRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(QuotationAddDTO quotationAddDTO) {
        // todo 验证船公司  航线代码  起运港 目的港 以及 有效期 都存在两条或者以上是，提示不允许重复
        //查船公司
        Carrier carrier = carrierService.get(quotationAddDTO.getCarrierId());
        //查找航线
        Route route = routeService.get(quotationAddDTO.getRouteId());
        //查起运港和目的港
        Port portFirst = portService.get(quotationAddDTO.getPortShipmentId());
        Port portLast = portService.get(quotationAddDTO.getPortDestinationId());
        //查起运港组合和目的港组合
        PortCombinationAssociated pcaFirst = combinationAssociatedService.get(quotationAddDTO.getPortShipmentId());
        PortCombinationAssociated pcaLast = combinationAssociatedService.get(quotationAddDTO.getPortDestinationId());
        //查找成本代码
        CostCode costCode = costCodeService.get(quotationAddDTO.getCostId());
        //插入数据到报价主表
        Quotation quotation = Quotation.builder().carrierId(quotationAddDTO.getCarrierId())
                .carrierCode(carrier.getCarrierCode())
                .routeId(quotationAddDTO.getRouteId())
                .routeCode(route.getRouteCode())
                .portShipmentId(quotationAddDTO.getPortShipmentId())
                .portShipment(portFirst.getPortEN())
                .portShipmentCombinationId(pcaFirst.getCombinationId())
                .portDestinationId(quotationAddDTO.getPortDestinationId())
                .portDestination(portLast.getPortEN())
                .portDestinationCombinationId(pcaLast.getCombinationId())
                .closingTime(quotationAddDTO.getClosingTime())
                .sailingTime(quotationAddDTO.getSailingTime())
                .transitPort(quotationAddDTO.getTransitPort())
                .voyage(quotationAddDTO.getVoyage())
                .currency(quotationAddDTO.getCurrency())
                .remarks(quotationAddDTO.getRemarks())
                .yermValidity(quotationAddDTO.getYermValidity())
                .costId(quotationAddDTO.getCostId())
                .costCode(costCode.getCode())
                .publish(quotationAddDTO.getPublish()).build();
        this.add(quotation);

        int quotationId = quotation.getId();
        List<QuotationCostAddDTO> quotationCostAddDTOList = quotationAddDTO.getQuotationCostAddDTOList();
        for (QuotationCostAddDTO quotationCostAddDTO : quotationCostAddDTOList) {
            QuotationCost quotationCost = QuotationCost.builder().quotationId(quotationId)
                    .costService(quotationCostAddDTO.getCostService())
                    .costServiceCode(quotationCostAddDTO.getCostServiceCode())
                    .cabinetType(quotationCostAddDTO.getCabinetType()).build();
            quotationCostService.add(quotationCost);
        }
    }


    @Override
    public QuotationVO getOne(Integer id) {
        List<QuotationCost> quotationCosts = quotationCostRepository.findByQuotationId(id);
        List<QuotationCostVO> vos = quotationCosts.stream().map(
                it -> QuotationCostVO.builder()
                        .id(it.getId())
                        .quotationId(it.getQuotationId())
                        .costService(it.getCostService())
                        .costServiceCode(it.getCostServiceCode())
                        .cabinetType(it.getCabinetType()).build()
        ).collect(Collectors.toList());

        Quotation quotation = this.get(id);
        QuotationVO quotationVO = QuotationVO.builder()
                .id(id)
                .carrierId(quotation.getCarrierId())
                .carrierCode(quotation.getCarrierCode())
                .routeId(quotation.getRouteId())
                .routeCode(quotation.getRouteCode())
                .portShipmentId(quotation.getPortShipmentId())
                .portShipment(quotation.getPortShipment())
                .portDestinationId(quotation.getPortDestinationId())
                .portDestination(quotation.getPortDestination())
                .closingTime(quotation.getClosingTime())
                .sailingTime(quotation.getSailingTime())
                .transitPort(quotation.getTransitPort())
                .voyage(quotation.getVoyage())
                .currency(quotation.getCurrency())
                .remarks(quotation.getRemarks())
                .yermValidity(quotation.getYermValidity())
                .costId(quotation.getCostId())
                .costCode(quotation.getCostCode())
                .quotationCostVOList(vos).build();
        return quotationVO;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QuotationUpdateDTO quotationUpdateDTO) {
        // todo 验证船公司  航线代码  起运港 目的港 以及 有效期 都存在两条或者以上是，提示不允许重复
        //查船公司
        Carrier carrier = carrierService.get(quotationUpdateDTO.getCarrierId());
        //查找航线
        Route route = routeService.get(quotationUpdateDTO.getRouteId());
        //查起运港和目的港
        Port portFirst = portService.get(quotationUpdateDTO.getPortShipmentId());
        Port portLast = portService.get(quotationUpdateDTO.getPortDestinationId());
        //查起运港组合和目的港组合
        PortCombinationAssociated pcaFirst = combinationAssociatedService.get(quotationUpdateDTO.getPortShipmentId());
        PortCombinationAssociated pcaLast = combinationAssociatedService.get(quotationUpdateDTO.getPortDestinationId());
        //查找成本代码
        CostCode costCode = costCodeService.get(quotationUpdateDTO.getCostId());
        //插入数据到报价主表
        Quotation quotation = this.get(quotationUpdateDTO.getId());
        quotation.setCarrierId(quotationUpdateDTO.getCarrierId());
        quotation.setCarrierCode(carrier.getCarrierCode());
        quotation.setRouteId(quotationUpdateDTO.getRouteId());
        quotation.setRouteCode(route.getRouteCode());
        quotation.setPortShipmentId(quotationUpdateDTO.getPortShipmentId());
        quotation.setPortShipment(portFirst.getPortEN());
        quotation.setPortDestinationCombinationId(pcaFirst.getCombinationId());
        quotation.setPortDestinationId(quotationUpdateDTO.getPortDestinationId());
        quotation.setPortDestination(portLast.getPortEN());
        quotation.setPortDestinationCombinationId(pcaLast.getCombinationId());
        quotation.setClosingTime(quotationUpdateDTO.getClosingTime());
        quotation.setSailingTime(quotationUpdateDTO.getSailingTime());
        quotation.setTransitPort(quotationUpdateDTO.getTransitPort());
        quotation.setVoyage(quotationUpdateDTO.getVoyage());
        quotation.setCurrency(quotationUpdateDTO.getCurrency());
        quotation.setRemarks(quotationUpdateDTO.getRemarks());
        quotation.setYermValidity(quotationUpdateDTO.getYermValidity());
        quotation.setCostId(quotationUpdateDTO.getCostId());
        quotation.setCostCode(costCode.getCode());
        quotation.setPublish(quotationUpdateDTO.getPublish());

        List<QuotationCostUpdateDTO> quotationCostUpdateDTOS = quotationUpdateDTO.getQuotationCostUpdateDTOList();
        for (QuotationCostUpdateDTO qcud : quotationCostUpdateDTOS) {
            QuotationCost quotationCost = quotationCostService.get(qcud.getId());
            quotationCost.setCabinetType(qcud.getCabinetType());
            quotationCostService.update(quotationCost);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBacth(List<QuotationAddDTO> quotationAddDTOS) {
        for (QuotationAddDTO quotationAddDTO : quotationAddDTOS) {
            add(quotationAddDTO);
        }
    }


    @Override
    public Page<QuotationVO> find(String costServiceCode, QuotationQueryDTO quotationQueryDTO, Pageable pageable) {
        return quotationRepository.findAll(costServiceCode,quotationQueryDTO,pageable);
    }

    @Override
    public List<QuotationVO> callHistory(QuotationCallHistory quotationCallHistory) {
        return quotationRepository.callHistory(quotationCallHistory);
    }
}
