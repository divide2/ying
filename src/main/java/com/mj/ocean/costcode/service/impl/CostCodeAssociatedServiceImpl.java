package com.mj.ocean.costcode.service.impl;

import com.mj.core.basic.service.impl.SimpleBasicServiceImpl;
import com.mj.ocean.costcode.model.CostCodeAssociated;
import com.mj.ocean.costcode.repo.CostCodeAssociatedRepository;
import com.mj.ocean.costcode.service.CostCodeAssociatedService;
import com.mj.ocean.costcode.vo.CostCodeAssociatedVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zejun
 * @date 2018/7/17 10:21
 */
@Service
public class CostCodeAssociatedServiceImpl extends SimpleBasicServiceImpl<CostCodeAssociated,Integer,CostCodeAssociatedRepository>
        implements CostCodeAssociatedService {

    private final CostCodeAssociatedRepository costCodeAssociatedRepository;

    public CostCodeAssociatedServiceImpl(CostCodeAssociatedRepository costCodeAssociatedRepository) {
        this.costCodeAssociatedRepository = costCodeAssociatedRepository;
    }

    @Override
    public List<CostCodeAssociatedVO> findByCostCodeId(Integer costCodeId) {
        List<CostCodeAssociated> costCodeAssociatedList = costCodeAssociatedRepository.findByCostCodeId(costCodeId);
        List<CostCodeAssociatedVO> vos = costCodeAssociatedList.stream().map(
                costCodeAssociated -> CostCodeAssociatedVO.builder()
                        .id(costCodeAssociated.getId())
                        .costService(costCodeAssociated.getCostService())
                        .costCodeId(costCodeAssociated.getCostCodeId())
                        .cabinetType(costCodeAssociated.getCabinetType())
                        .floatingAmount(costCodeAssociated.getFloatingAmount()).build()
        ).collect(Collectors.toList());
        return vos;
    }
}
