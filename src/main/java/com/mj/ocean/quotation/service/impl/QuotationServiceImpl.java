package com.mj.ocean.quotation.service.impl;

import com.mj.core.data.properties.StatusProperties;
import com.mj.core.er.Loginer;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.carrier.model.Carrier;
import com.mj.general.port.model.Port;
import com.mj.general.route.model.Route;
import com.mj.ocean.basic.service.OceanGeneralService;
import com.mj.ocean.costcode.model.CostCode;
import com.mj.ocean.costcode.service.CostCodeService;
import com.mj.ocean.portcombination.model.PortCombinationAssociated;
import com.mj.ocean.portcombination.repo.CombinationAssociatedRepository;
import com.mj.ocean.quotation.dto.*;
import com.mj.ocean.quotation.model.Quotation;
import com.mj.ocean.quotation.model.QuotationCost;
import com.mj.ocean.quotation.repo.QuotationCostRepository;
import com.mj.ocean.quotation.repo.QuotationRepository;
import com.mj.ocean.quotation.service.QuotationCostService;
import com.mj.ocean.quotation.service.QuotationService;
import com.mj.ocean.quotation.vo.QuotationCostVO;
import com.mj.ocean.quotation.vo.QuotationInfoVO;
import com.mj.ocean.quotation.vo.QuotationOneVO;
import com.mj.ocean.quotation.vo.QuotationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zejun
 * @date 2018/7/17 18:25
 */
@Service
public class QuotationServiceImpl extends SimpleBasicServiceImpl<Quotation, Integer, QuotationRepository>
        implements QuotationService {
    private final QuotationRepository quotationRepository;
    private final QuotationCostRepository quotationCostRepository;
    private final QuotationCostService quotationCostService;
    private final CostCodeService costCodeService;
    private final OceanGeneralService oceanGeneralService;
    private final CombinationAssociatedRepository combinationAssociatedRepository;
    private final StatusProperties status;


    public QuotationServiceImpl(QuotationRepository quotationRepository,
                                CostCodeService costCodeService,
                                QuotationCostService quotationCostService,
                                QuotationCostRepository quotationCostRepository,
                                OceanGeneralService oceanGeneralService,
                                CombinationAssociatedRepository combinationAssociatedRepository,
                                StatusProperties status) {
        this.quotationRepository = quotationRepository;
        this.costCodeService = costCodeService;
        this.quotationCostService = quotationCostService;
        this.quotationCostRepository = quotationCostRepository;
        this.oceanGeneralService = oceanGeneralService;
        this.combinationAssociatedRepository = combinationAssociatedRepository;
        this.status = status;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(QuotationAddDTO quotationAddDTO) {
        // todo 验证船公司  航线代码  起运港 目的港 以及 有效期 都存在两条或者以上是，提示不允许重复
        // todo 获取用户的公司id以及用户数据
        int companyId = 1;
        //查船公司
        Carrier carrier = oceanGeneralService.getCarrier(quotationAddDTO.getCarrierId());
        //查找航线
        Route route = oceanGeneralService.getRoute(quotationAddDTO.getRouteId());
        //查起运港和目的港
        Port portFirst = oceanGeneralService.getPort(quotationAddDTO.getPortShipmentId());
        Port portLast = oceanGeneralService.getPort(quotationAddDTO.getPortDestinationId());
        //查起运港组合和目的港组合
        PortCombinationAssociated pcaFirst = combinationAssociatedRepository.findByCompanyIdAndPortIdAndCarrierId(
                companyId, quotationAddDTO.getPortShipmentId(), quotationAddDTO.getCarrierId());
        PortCombinationAssociated pcaLast = combinationAssociatedRepository.findByCompanyIdAndPortIdAndCarrierId(
                companyId, quotationAddDTO.getPortDestinationId(), quotationAddDTO.getCarrierId());

        //查找成本代码
        CostCode costCode = new CostCode();
        if (quotationAddDTO.getCostId() != null) {
            costCode = costCodeService.get(quotationAddDTO.getCostId());
        }
        //插入数据到报价主表
        int quotationId = addQuotation(quotationAddDTO, carrier, route, portFirst, pcaFirst, portLast, pcaLast, costCode, companyId);
        //插入数据到报价关联表
        addQuotationCost(quotationAddDTO, quotationId);
    }

    private void addQuotationCost(QuotationAddDTO quotationAddDTO, int quotationId) {
        List<QuotationCostAddDTO> quotationCostAdds = quotationAddDTO.getQuotationCosts();
        for (QuotationCostAddDTO quotationCostAddDTO : quotationCostAdds) {
            QuotationCost quotationCost = QuotationCost.builder().quotationId(quotationId)
                    .type(quotationCostAddDTO.getType())
                    .originalPrice(quotationCostAddDTO.getOriginalPrice())
                    .commercePrice(quotationCostAddDTO.getCommercePrice())
                    .businessPrice(quotationCostAddDTO.getBusinessPrice())
                    .openPrice(quotationCostAddDTO.getOpenPrice()).build();
            quotationCostService.add(quotationCost);
        }
    }

    private Integer addQuotation(QuotationAddDTO quotationAddDTO, Carrier carrier, Route route,
                                 Port portFirst, PortCombinationAssociated pcaFirst,
                                 Port portLast, PortCombinationAssociated pcaLast,
                                 CostCode costCode, Integer companyId) {
        Quotation quotation = Quotation.builder().carrierId(quotationAddDTO.getCarrierId())
                .carrierCode(carrier.getCarrierCode())
                .routeId(quotationAddDTO.getRouteId())
                .routeCode(route.getRouteCode())
                .portShipmentId(quotationAddDTO.getPortShipmentId())
                .portShipment(portFirst.getPortEN())
                .portShipmentCombinationId(pcaFirst != null ? pcaFirst.getCombinationId() : null)
                .portDestinationId(quotationAddDTO.getPortDestinationId())
                .portDestination(portLast.getPortEN())
                .portDestinationCombinationId(pcaLast != null ? pcaLast.getCombinationId() : null)
                .etc(quotationAddDTO.getEtc())
                .etd(quotationAddDTO.getEtd())
                .transitPort(quotationAddDTO.getTransitPort())
                .tt(quotationAddDTO.getTt())
                .currency(quotationAddDTO.getCurrency())
                .remarks(quotationAddDTO.getRemarks())
                .effectiveStartTime(quotationAddDTO.getEffectiveStartTime())
                .effectiveEndTime(quotationAddDTO.getEffectiveEndTime())
                .costId(quotationAddDTO.getCostId())
                .costCode(costCode.getCode())
                .publish(quotationAddDTO.getPublish())
                .costServiceCode(quotationAddDTO.getCostServiceCode())
                .companyId(companyId)
                .createdUserid(Loginer.userId())
                .createdUsername(Loginer.username())
                .createdDate(LocalDateTime.now()).build();
        this.add(quotation);
        return quotation.getId();
    }

    @Override
    public QuotationOneVO getOne(Integer id) {
        List<QuotationCost> quotationCosts = quotationCostRepository.findByQuotationId(id);
        List<QuotationCostVO> vos = quotationCosts.stream().map(
                it -> QuotationCostVO.builder()
                        .id(it.getId())
                        .quotationId(it.getQuotationId())
                        .originalPrice(it.getOriginalPrice())
                        .businessPrice(it.getBusinessPrice())
                        .commercePrice(it.getCommercePrice())
                        .openPrice(it.getOpenPrice())
                        .type(it.getType()).build()
        ).collect(Collectors.toList());
        QuotationOneVO vo = getVo(id, vos);
        return vo;
    }

    private QuotationOneVO getVo(Integer id, List<QuotationCostVO> vos) {
        Quotation quotation = this.get(id);
        QuotationOneVO quotationOne = QuotationOneVO.builder()
                .id(id)
                .carrierId(quotation.getCarrierId())
                .carrierCode(quotation.getCarrierCode())
                .routeId(quotation.getRouteId())
                .routeCode(quotation.getRouteCode())
                .portShipmentId(quotation.getPortShipmentId())
                .portShipment(quotation.getPortShipment())
                .portDestinationId(quotation.getPortDestinationId())
                .portDestination(quotation.getPortDestination())
                .etc(quotation.getEtc())
                .etd(quotation.getEtd())
                .transitPort(quotation.getTransitPort())
                .tt(quotation.getTt())
                .currency(quotation.getCurrency())
                .remarks(quotation.getRemarks())
                .effectiveStartTime(quotation.getEffectiveStartTime())
                .effectiveEndTime(quotation.getEffectiveEndTime())
                .costId(quotation.getCostId())
                .costCode(quotation.getCostCode())
                .costServiceCode(quotation.getCostServiceCode())
                .quotationCosts(vos).build();
        return quotationOne;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QuotationUpdateDTO quotationUpdateDTO) {
        // todo 验证船公司  航线代码  起运港 目的港 以及 有效期 都存在两条或者以上是，提示不允许重复
        //todo 获取用户的公司id以及用户数据
        int companyId = 1;
        //查船公司
        Carrier carrier = oceanGeneralService.getCarrier(quotationUpdateDTO.getCarrierId());
        //查找航线
        Route route = oceanGeneralService.getRoute(quotationUpdateDTO.getRouteId());
        //查起运港和目的港
        Port portFirst = oceanGeneralService.getPort(quotationUpdateDTO.getPortShipmentId());
        Port portLast = oceanGeneralService.getPort(quotationUpdateDTO.getPortDestinationId());
        //查起运港组合和目的港组合
        PortCombinationAssociated pcaFirst = combinationAssociatedRepository.findByCompanyIdAndPortIdAndCarrierId(
                companyId, quotationUpdateDTO.getPortShipmentId(), quotationUpdateDTO.getCarrierId());
        PortCombinationAssociated pcaLast = combinationAssociatedRepository.findByCompanyIdAndPortIdAndCarrierId(
                companyId, quotationUpdateDTO.getPortDestinationId(), quotationUpdateDTO.getCarrierId());

        //查找成本代码
        CostCode costCode = new CostCode();
        if (quotationUpdateDTO.getCostId() != null) {
            costCode = costCodeService.get(quotationUpdateDTO.getCostId());
        }

        //插入数据到报价主表
        updateQuotation(quotationUpdateDTO, carrier, route, portFirst, pcaFirst, portLast, pcaLast, costCode);

        //根据报价id删除报价关联表数据
        quotationCostRepository.deleteByQuotationId(quotationUpdateDTO.getId());
        //插入数据到报价关联表
        List<QuotationCostUpdateDTO> quotationCostUpdates = quotationUpdateDTO.getQuotationCosts();
        for (QuotationCostUpdateDTO qcud : quotationCostUpdates) {
            QuotationCost quotationCost = new QuotationCost();
            quotationCost.setType(qcud.getType());
            quotationCost.setOriginalPrice(qcud.getOriginalPrice());
            quotationCost.setCommercePrice(qcud.getCommercePrice());
            quotationCost.setBusinessPrice(qcud.getBusinessPrice());
            quotationCost.setOpenPrice(qcud.getOpenPrice());
            quotationCost.setQuotationId(quotationUpdateDTO.getId());
            quotationCostService.add(quotationCost);
        }
    }

    private void updateQuotation(QuotationUpdateDTO quotationUpdateDTO, Carrier carrier, Route route,
                                 Port portFirst, PortCombinationAssociated pcaFirst,
                                 Port portLast, PortCombinationAssociated pcaLast, CostCode costCode) {
        Quotation quotation = this.get(quotationUpdateDTO.getId());
        quotation.setCarrierId(quotationUpdateDTO.getCarrierId());
        quotation.setCarrierCode(carrier.getCarrierCode());
        quotation.setRouteId(quotationUpdateDTO.getRouteId());
        quotation.setRouteCode(route.getRouteCode());
        quotation.setPortShipmentId(quotationUpdateDTO.getPortShipmentId());
        quotation.setPortShipment(portFirst.getPortEN());
        quotation.setPortDestinationCombinationId(pcaFirst != null ? pcaFirst.getCombinationId() : null);
        quotation.setPortDestinationId(quotationUpdateDTO.getPortDestinationId());
        quotation.setPortDestination(portLast.getPortEN());
        quotation.setPortDestinationCombinationId(pcaLast != null ? pcaLast.getCombinationId() : null);
        quotation.setEtc(quotationUpdateDTO.getEtc());
        quotation.setEtd(quotationUpdateDTO.getEtd());
        quotation.setTransitPort(quotationUpdateDTO.getTransitPort());
        quotation.setTt(quotationUpdateDTO.getTt());
        quotation.setCurrency(quotationUpdateDTO.getCurrency());
        quotation.setRemarks(quotationUpdateDTO.getRemarks());
        quotation.setEffectiveStartTime(quotationUpdateDTO.getEffectiveStartTime());
        quotation.setEffectiveEndTime(quotationUpdateDTO.getEffectiveEndTime());
        quotation.setCostId(quotationUpdateDTO.getCostId());
        quotation.setCostCode(costCode.getCode());
        quotation.setPublish(quotationUpdateDTO.getPublish());
        quotation.setCostServiceCode(quotationUpdateDTO.getCostServiceCode());
        quotation.setUpdateUserid(Loginer.userId());
        quotation.setUpdateUsername(Loginer.username());
        quotation.setUpdateDate(LocalDateTime.now());
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
    public Page<QuotationOneVO> find(String costServiceCode, QuotationQueryDTO quotationQueryDTO, Pageable pageable) {
        Page<QuotationVO> quotations = quotationRepository.findAll(costServiceCode, quotationQueryDTO, pageable);
        return quotations.map(it -> {
            QuotationOneVO quotationOneVO = QuotationOneVO.fromQuotationVO(it);
            List<QuotationCost> quotationCosts = quotationCostRepository.findByQuotationId(it.getId());
            List<QuotationCostVO> quotationCostVOS = quotationCosts.stream().map(QuotationCostVO::of).collect(Collectors.toList());
            quotationOneVO.setQuotationCosts(quotationCostVOS);
            return quotationOneVO;
        });
    }

    private List<QuotationInfoVO> toList(List<QuotationVO> quotationVOS) {
        List<QuotationInfoVO> lists = new ArrayList<>();
        for (QuotationVO vo : quotationVOS) {
            QuotationInfoVO quotationInfoVO = new QuotationInfoVO();
            List<QuotationCost> quotationCosts = quotationCostRepository.findByQuotationId(vo.getId());
            List<QuotationCostVO> quotationCostVOS = quotationCosts.stream().map(
                    it -> QuotationCostVO.builder()
                            .id(it.getId())
                            .type(it.getType())
                            .originalPrice(it.getOriginalPrice())
                            .businessPrice(it.getBusinessPrice())
                            .commercePrice(it.getCommercePrice())
                            .openPrice(it.getOpenPrice())
                            .quotationId(it.getQuotationId()).build()
            ).collect(Collectors.toList());
            quotationInfoVO.setQuotationVO(vo);
            quotationInfoVO.setQuotationCosts(quotationCostVOS);
            lists.add(quotationInfoVO);
        }
        return lists;
    }

    @Override
    public List<QuotationInfoVO> callHistory(QuotationCallHistory quotationCallHistory) {
        //todo 标准报价
        int companyId = 1;
        String costServiceCode = "general";
        List<Quotation> quotation = quotationRepository.findOrderByCreatedDateDesc(companyId);
        List<QuotationVO> quotationVOS = quotationRepository.callHistory(quotationCallHistory, quotation.get(0));
        return toList(quotationVOS);
    }

    @Override
    public void toggleEnable(Integer id) {
        Quotation quotation = this.get(id);
        if (status.getEnable().equals(quotation.getEnabled())) {
            quotation.setEnabled(status.getDisable());
        } else {
            quotation.setEnabled(status.getEnable());
        }
        this.update(quotation);
    }
}
