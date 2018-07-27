package com.mj.ocean.portcombination.service.impl;

import com.google.common.collect.Lists;
import com.mj.core.data.properties.StatusProperties;
import com.mj.core.exception.AlreadyExistsException;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.ocean.portcombination.dto.CombinationAddDTO;
import com.mj.ocean.portcombination.dto.CombinationQueryDTO;
import com.mj.ocean.portcombination.dto.CombinationUpdateDTO;
import com.mj.ocean.portcombination.model.PortCombination;
import com.mj.ocean.portcombination.model.PortCombinationAssociated;
import com.mj.ocean.portcombination.model.QPortCombination;
import com.mj.ocean.portcombination.repo.CombinationAssociatedRepository;
import com.mj.ocean.portcombination.repo.PortCombinationRepository;
import com.mj.ocean.portcombination.service.CombinationService;
import com.mj.ocean.portcombination.vo.CombinationAllVO;
import com.mj.ocean.portcombination.vo.CombinationAssociatedVO;
import com.mj.ocean.portcombination.vo.CombinationUpdateVO;
import com.mj.ocean.portcombination.vo.CombinationVO;
import com.mj.ocean.portcombination.vo.PortCombinationVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author zejun
 * @date 2018/7/13 15:44
 */
@Service
public class CombinationServiceImpl extends SimpleBasicServiceImpl<PortCombination, Integer, PortCombinationRepository>
        implements CombinationService {

    private final PortCombinationRepository portCombinationRepository;
    private final CombinationAssociatedServiceImpl CombinationAssociatedService;
    private final CombinationAssociatedRepository combinationAssociatedRepository;
    private final StatusProperties status;

    public CombinationServiceImpl(PortCombinationRepository portCombinationRepository,
                                  CombinationAssociatedServiceImpl combinationAssociatedService,
                                  CombinationAssociatedRepository combinationAssociatedRepository,
                                  StatusProperties status) {
        this.portCombinationRepository = portCombinationRepository;
        CombinationAssociatedService = combinationAssociatedService;
        this.combinationAssociatedRepository = combinationAssociatedRepository;
        this.status = status;
    }

    @Override
    public Page<CombinationVO> find(CombinationQueryDTO combinationQueryDTO, Pageable pageable) {
        Integer companyId = 1;
        return portCombinationRepository.findAll(companyId, combinationQueryDTO, pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CombinationAddDTO combinationAddDTO) {
        //todo 根据登陆人信息获取客户公司id，存入数据
        Integer companyId = 1;
        Integer carrierId = combinationAddDTO.getCarrierId();
        List<CombinationAddDTO.CombinationPort> combinationPorts = combinationAddDTO.getCombinationPorts();
        for (CombinationAddDTO.CombinationPort combinationPort : combinationPorts) {
            String combinationName = combinationPort.getCombinationName();
            List<Integer> portIds = combinationPort.getPortIds();
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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CombinationUpdateDTO combinationUpdateDTO) {
        //todo 根据登陆人信息获取客户公司id，存入数据
        PortCombination portCombination = get(combinationUpdateDTO.getId());
        portCombination.setCombinationName(combinationUpdateDTO.getCombinationName());
        update(portCombination);

        combinationAssociatedRepository.deleteByCombinationId(combinationUpdateDTO.getId());

        List<Integer> portIds = combinationUpdateDTO.getPortIds();
        for (Integer portId : portIds) {
            PortCombinationAssociated portCombinationAssociated = PortCombinationAssociated.builder()
                    .carrierId(combinationUpdateDTO.getCarrierId())
                    .portId(portId)
                    .combinationId(combinationUpdateDTO.getId())
                    .companyId(portCombination.getCompanyId()).build();
            CombinationAssociatedService.add(portCombinationAssociated);
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
            PortCombination portCombination = portCombinationRepository.getByCombinationNameAndCompanyId(combinationName, companyId);
            if (portCombination != null) {
                throw new AlreadyExistsException();
            }
        }
    }

    @Override
    public void toggleEnable(Integer id) {
        PortCombination portCombination = this.get(id);
        if (status.getEnable().equals(portCombination.getEnabled())) {
            portCombination.setEnabled(status.getDisable());
        } else {
            portCombination.setEnabled(status.getEnable());
        }
        this.update(portCombination);
    }

    @Override
    public CombinationUpdateVO getUpdateInfo(Integer id) {
        // 首先获取组合信息
        PortCombination combination = portCombinationRepository.getOne(id);
        // 然后获取组合的公司和港口 组合的公司只有一个 港口有多个
        List<CombinationAssociatedVO> carrierAndPorts = combinationAssociatedRepository.findByCombinationId(id);
        CombinationUpdateVO vo = new CombinationUpdateVO();
        vo.setCombinationId(combination.getId());
        vo.setCombinationName(combination.getCombinationName());
        vo.setCarrierId(carrierAndPorts.get(0).getCarrierId());
        vo.setPortIds(carrierAndPorts.stream().map(CombinationAssociatedVO::getPortId).collect(Collectors.toList()));
        return vo;
    }

    @Override
    public List<PortCombination> findAll() {
        //todo 客户公司id
        int companyId = 1;
        QPortCombination portCombination = QPortCombination.portCombination;
        BooleanExpression predicate = portCombination.enabled.eq('Y');
        predicate = predicate.and(portCombination.companyId.eq(companyId));
        return Lists.newArrayList(portCombinationRepository.findAll(predicate));
    }

    @Override
    public List<CombinationAllVO> findAllByCarrierId(Integer carrierId) {
        return portCombinationRepository.findAllByCarrierId(carrierId);
    }
}
