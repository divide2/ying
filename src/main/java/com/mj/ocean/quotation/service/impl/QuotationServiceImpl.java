package com.mj.ocean.quotation.service.impl;

import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.carrier.model.Carrier;
import com.mj.general.carrier.service.CarrierService;
import com.mj.general.port.model.Port;
import com.mj.general.port.service.PortService;
import com.mj.general.route.model.Route;
import com.mj.general.route.service.RouteService;
import com.mj.ocean.basic.service.OceanGeneralService;
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
    private final QuotationCostRepository quotationCostRepository;
    private final QuotationCostService quotationCostService;
    private final CombinationAssociatedService combinationAssociatedService;
    private final CostCodeService costCodeService;
    private final OceanGeneralService oceanGeneralService;


    public QuotationServiceImpl(QuotationRepository quotationRepository,CombinationAssociatedService combinationAssociatedService,
                                CostCodeService costCodeService,QuotationCostService quotationCostService,
                                QuotationCostRepository quotationCostRepository,OceanGeneralService oceanGeneralService) {
        this.quotationRepository = quotationRepository;
        this.combinationAssociatedService = combinationAssociatedService;
        this.costCodeService = costCodeService;
        this.quotationCostService = quotationCostService;
        this.quotationCostRepository = quotationCostRepository;
        this.oceanGeneralService = oceanGeneralService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(QuotationAddDTO quotationAddDTO) {
        // todo 验证船公司  航线代码  起运港 目的港 以及 有效期 都存在两条或者以上是，提示不允许重复
        //todo 获取用户的公司id以及用户数据
        //查船公司
        Carrier carrier = oceanGeneralService.getCarrier(quotationAddDTO.getCarrierId());
        //查找航线
        Route route = oceanGeneralService.getRoute(quotationAddDTO.getRouteId());
        //查起运港和目的港
        Port portFirst = oceanGeneralService.getPort(quotationAddDTO.getPortShipmentId());
        Port portLast = oceanGeneralService.getPort(quotationAddDTO.getPortDestinationId());
        //查起运港组合和目的港组合
        PortCombinationAssociated pcaFirst = combinationAssociatedService.get(quotationAddDTO.getPortShipmentId());
        PortCombinationAssociated pcaLast = combinationAssociatedService.get(quotationAddDTO.getPortDestinationId());
        //查找成本代码
        CostCode costCode = costCodeService.get(quotationAddDTO.getCostId());

        //插入数据到报价主表
        int quotationId = addQuotation(quotationAddDTO,carrier,route,portFirst,pcaFirst,portLast,pcaLast,costCode);
        //插入数据到报价关联表
        addQuotationCost(quotationAddDTO,quotationId);
    }

    private void addQuotationCost(QuotationAddDTO quotationAddDTO,int quotationId) {
        List<QuotationCostAddDTO> quotationCostAdds = quotationAddDTO.getQuotationCostAdds();
        for (QuotationCostAddDTO quotationCostAddDTO : quotationCostAdds) {
            QuotationCost quotationCost = QuotationCost.builder().quotationId(quotationId)
                    .costService(quotationCostAddDTO.getCostService())
                    .costServiceCode(quotationCostAddDTO.getCostServiceCode())
                    .cabinetType(quotationCostAddDTO.getCabinetType()).build();
            quotationCostService.add(quotationCost);
        }
    }

    private Integer addQuotation(QuotationAddDTO quotationAddDTO,Carrier carrier,Route route,
                                 Port portFirst,PortCombinationAssociated pcaFirst,
                                 Port portLast,PortCombinationAssociated pcaLast,CostCode costCode) {
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
        return quotation.getId();
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
        return vo(id,vos);
    }

    private QuotationVO vo(Integer id,List<QuotationCostVO> vos) {
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
                .quotationCostVOs(vos).build();
        return quotationVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QuotationUpdateDTO quotationUpdateDTO) {
        // todo 验证船公司  航线代码  起运港 目的港 以及 有效期 都存在两条或者以上是，提示不允许重复
        //todo 获取用户的公司id以及用户数据
        //查船公司
        Carrier carrier = oceanGeneralService.getCarrier(quotationUpdateDTO.getCarrierId());
        //查找航线
        Route route = oceanGeneralService.getRoute(quotationUpdateDTO.getRouteId());
        //查起运港和目的港
        Port portFirst = oceanGeneralService.getPort(quotationUpdateDTO.getPortShipmentId());
        Port portLast = oceanGeneralService.getPort(quotationUpdateDTO.getPortDestinationId());
        //查起运港组合和目的港组合
        PortCombinationAssociated pcaFirst = combinationAssociatedService.get(quotationUpdateDTO.getPortShipmentId());
        PortCombinationAssociated pcaLast = combinationAssociatedService.get(quotationUpdateDTO.getPortDestinationId());
        //查找成本代码
        CostCode costCode = costCodeService.get(quotationUpdateDTO.getCostId());
        //插入数据到报价主表
        updateQuotation(quotationUpdateDTO,carrier,route,portFirst,pcaFirst,portLast,pcaLast,costCode);

        List<QuotationCostUpdateDTO> quotationCostUpdates = quotationUpdateDTO.getQuotationCostUpdates();
        for (QuotationCostUpdateDTO qcud : quotationCostUpdates) {
            QuotationCost quotationCost = quotationCostService.get(qcud.getId());
            quotationCost.setCabinetType(qcud.getCabinetType());
            quotationCostService.update(quotationCost);
        }
    }

    private void updateQuotation(QuotationUpdateDTO quotationUpdateDTO,Carrier carrier,Route route,
                                 Port portFirst,PortCombinationAssociated pcaFirst,
                                 Port portLast,PortCombinationAssociated pcaLast,CostCode costCode) {
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
        this.update(quotation);
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
