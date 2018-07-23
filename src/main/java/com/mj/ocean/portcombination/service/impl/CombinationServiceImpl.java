package com.mj.ocean.portcombination.service.impl;

import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.ocean.portcombination.dto.CombinationAddDTO;
import com.mj.ocean.portcombination.dto.CombinationQueryDTO;
import com.mj.ocean.portcombination.dto.CombinationUpdateDTO;
import com.mj.ocean.portcombination.model.PortCombination;
import com.mj.ocean.portcombination.model.PortCombinationAssociated;
import com.mj.ocean.portcombination.repo.CombinationAssociatedRepository;
import com.mj.ocean.portcombination.repo.PortCombinationRepository;
import com.mj.ocean.portcombination.service.CombinationService;
import com.mj.ocean.portcombination.vo.CombinationAssociatedVO;
import com.mj.ocean.portcombination.vo.CombinationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author zejun
 * @date 2018/7/13 15:44
 */
@Service
public class CombinationServiceImpl extends SimpleBasicServiceImpl<PortCombination,Integer,PortCombinationRepository>
        implements CombinationService {

    private final PortCombinationRepository portCombinationRepository;
    private final CombinationAssociatedServiceImpl CombinationAssociatedService;
    private final CombinationAssociatedRepository combinationAssociatedRepository;


    public CombinationServiceImpl(PortCombinationRepository portCombinationRepository,
                                  CombinationAssociatedServiceImpl combinationAssociatedService,
                                  CombinationAssociatedRepository combinationAssociatedRepository) {
        this.portCombinationRepository = portCombinationRepository;
        CombinationAssociatedService = combinationAssociatedService;
        this.combinationAssociatedRepository = combinationAssociatedRepository;
    }

    @Override
    public Page<CombinationVO> find(CombinationQueryDTO combinationQueryDTO, Pageable pageable) {
        return portCombinationRepository.findAll(combinationQueryDTO,pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CombinationAddDTO combinationAddDTO) {
        PortCombination portCombination = PortCombination.builder().
                combinationName(combinationAddDTO.getCombinationName()).build();
        add(portCombination);
        int combinationId = portCombination.getId();

        String[] portIds = combinationAddDTO.getPortIds().split(",");
        String[] carrierIds = combinationAddDTO.getCarrierIds().split(",");
        for (String carrierId : carrierIds) {
            for (String portId : portIds) {
                PortCombinationAssociated portCombinationAssociated = PortCombinationAssociated.builder()
                        .carrierId(Integer.parseInt(carrierId))
                        .portId(Integer.parseInt(portId))
                        .combinationId(combinationId).build();
                CombinationAssociatedService.add(portCombinationAssociated);
            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CombinationUpdateDTO combinationUpdateDTO) {
        PortCombination portCombination = get(combinationUpdateDTO.getId());
        portCombination.setCombinationName(combinationUpdateDTO.getCombinationName());
        update(portCombination);

        combinationAssociatedRepository.deleteByCombinationId(combinationUpdateDTO.getId());

        String[] portIds = combinationUpdateDTO.getPortIds().split(",");
        String[] carrierIds = combinationUpdateDTO.getCarrierIds().split(",");
        for (String carrierId : carrierIds) {
            for (String portId : portIds) {
                PortCombinationAssociated portCombinationAssociated = PortCombinationAssociated.builder()
                        .carrierId(Integer.parseInt(carrierId))
                        .portId(Integer.parseInt(portId))
                        .combinationId(combinationUpdateDTO.getId()).build();
                CombinationAssociatedService.add(portCombinationAssociated);
            }
        }
    }

    @Override
    public CombinationAssociatedVO getDetail(Integer id) {
        return combinationAssociatedRepository.findByCombinationId(id);
    }
}
