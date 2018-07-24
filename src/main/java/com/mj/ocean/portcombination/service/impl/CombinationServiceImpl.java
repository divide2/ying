package com.mj.ocean.portcombination.service.impl;

import com.mj.core.exception.AlreadyExistsException;
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
import com.mj.ocean.portcombination.vo.PortCombinationVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        Integer companyId = 1;
        return portCombinationRepository.findAll(companyId,combinationQueryDTO,pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CombinationAddDTO combinationAddDTO) {
        //todo 根据登陆人信息获取客户公司id，存入数据
        Integer companyId = 1;
        Integer carrierId = combinationAddDTO.getCarrierId();
        List<CombinationAddDTO.CombinationrPort> combinationrPorts = combinationAddDTO.getCombinationrPorts();
        for (CombinationAddDTO.CombinationrPort combinationrPort : combinationrPorts) {
            List<String> combinationNames = combinationrPort.getCombinationNames();
            List<Integer> portIds = combinationrPort.getPortIds();
            for (String combinationName : combinationNames) {
                PortCombination portCombination = PortCombination.builder()
                        .combinationName(combinationName)
                        .companyId(companyId).build();
                this.add(portCombination);
                for (Integer portId : portIds) {
                    PortCombinationAssociated portCombinationAssociated = PortCombinationAssociated.builder()
                            .carrierId(carrierId)
                            .portId(portId)
                            .combinationId(portCombination.getId())
                            .companyId(companyId).build();
                    CombinationAssociatedService.add(portCombinationAssociated);
                }
            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CombinationUpdateDTO combinationUpdateDTO) {
        //todo 根据登陆人信息获取客户公司id，存入数据
        PortCombination portCombination = get(combinationUpdateDTO.getId());
        portCombination.setCombinationName(combinationUpdateDTO.getCombinationName());
        update(portCombination);

        combinationAssociatedRepository.deleteByCombinationId(combinationUpdateDTO.getId());

        List<CombinationUpdateDTO.Port> ports = combinationUpdateDTO.getPorts();
        for (CombinationUpdateDTO.Port port : ports) {
            List<Integer> portIds = port.getPortIds();
            for (Integer portId : portIds) {
                PortCombinationAssociated portCombinationAssociated = PortCombinationAssociated.builder()
                        .carrierId(combinationUpdateDTO.getCarrierId())
                        .portId(portId)
                        .combinationId(combinationUpdateDTO.getId())
                        .companyId(portCombination.getCompanyId()).build();
                CombinationAssociatedService.add(portCombinationAssociated);
            }
        }
    }

    @Override
    public List<CombinationAssociatedVO> getDetail(Integer id) {
        return combinationAssociatedRepository.findByCombinationId(id);
    }

    @Override
    public void check(String combinationName) {
        //todo 获取用户信息
        Integer companyId = 1;
        if (StringUtils.isNotEmpty(combinationName)) {
            PortCombination portCombination = portCombinationRepository.getByCombinationNameAndCompanyId(combinationName,companyId);
            if (portCombination != null) {
                throw new AlreadyExistsException();
            }
        }
    }
}
