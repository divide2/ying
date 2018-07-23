package com.mj.ocean.costcode.service.impl;

import com.mj.core.exception.AlreadyExistsException;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.ocean.costcode.dto.*;
import com.mj.ocean.costcode.model.CostCode;
import com.mj.ocean.costcode.model.CostCodeAssociated;
import com.mj.ocean.costcode.model.QCostCode;
import com.mj.ocean.costcode.repo.CostCodeAssociatedRepository;
import com.mj.ocean.costcode.repo.CostCodeRepository;
import com.mj.ocean.costcode.service.CostCodeAssociatedService;
import com.mj.ocean.costcode.service.CostCodeService;
import com.mj.ocean.costcode.vo.CostCodeAssociatedVO;
import com.mj.ocean.costcode.vo.CostCodeVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zejun
 * @date 2018/7/17 10:11
 */
@Service
public class CostCodeServiceImpl extends SimpleBasicServiceImpl<CostCode,Integer, CostCodeRepository>
        implements CostCodeService {

    private final CostCodeRepository costCodeRepository;
    private final CostCodeAssociatedService costCodeAssociatedService;
    private final CostCodeAssociatedRepository costCodeAssociatedRepository;


    public CostCodeServiceImpl(CostCodeRepository costCodeRepository,
                               CostCodeAssociatedService costCodeAssociatedService,
                               CostCodeAssociatedRepository costCodeAssociatedRepository) {
        this.costCodeRepository = costCodeRepository;
        this.costCodeAssociatedService = costCodeAssociatedService;
        this.costCodeAssociatedRepository = costCodeAssociatedRepository;
    }

    @Override
    public void add(CostCodeAddDTO costCodeAddDTO) {
        //todo 根据登陆人信息获取客户公司id，存入数据
        CostCode costCode = CostCode.builder().code(costCodeAddDTO.getCode())
                .createdUserid(11111)
                .createdUsername("eqwffew")
                .createdDate(LocalDateTime.now()).build();
        add(costCode);

        int costCodeId = costCode.getId();
        List<CostCodeAssociatedAddDTO> costCodeAssociateds = costCodeAddDTO.getCostCodeAssociateds();
        for (CostCodeAssociatedAddDTO costCodeAssociatedAddDTO : costCodeAssociateds) {
            CostCodeAssociated costCodeAssociated = CostCodeAssociated.builder()
                    .costService(costCodeAssociatedAddDTO.getCostService())
                    .cabinetType(costCodeAssociatedAddDTO.getCabinetType())
                    .floatingAmount(costCodeAssociatedAddDTO.getFloatingAmount())
                    .costCodeId(costCodeId).build();
            costCodeAssociatedService.add(costCodeAssociated);
        }
    }


    @Override
    public void update(CostCodeUpdateDTO costCodeUpdateDTO) {
        //todo 根据登陆人信息获取客户公司id，存入数据
        CostCode costCode = get(costCodeUpdateDTO.getId());
        costCode.setCode(costCodeUpdateDTO.getCode());
        costCode.setUpdateUserid(222);
        costCode.setUpdateUsername("fffgfg");
        costCode.setUpdateDate(LocalDateTime.now());
        update(costCode);

        //先删除关联表数据，再插入
        costCodeAssociatedRepository.deleteByCostCodeId(costCodeUpdateDTO.getId());

        List<CostCodeAssociatedUpdateDTO> costCodeAssociatedUpdateDTOS = costCodeUpdateDTO.getCostCodeAssociatedUpdates();
        for (CostCodeAssociatedUpdateDTO ccaud : costCodeAssociatedUpdateDTOS) {
            CostCodeAssociated costCodeAssociated1 = CostCodeAssociated.builder()
                    .costService(ccaud.getCostService())
                    .cabinetType(ccaud.getCabinetType())
                    .floatingAmount(ccaud.getFloatingAmount())
                    .costCodeId(costCodeUpdateDTO.getId()).build();
            costCodeAssociatedService.add(costCodeAssociated1);
        }
    }

    @Override
    public CostCodeVO getDetail(Integer id) {
        CostCode costCode = get(id);
        List<CostCodeAssociated> costCodeAssociatedList = costCodeAssociatedRepository.findByCostCodeId(id);

        List<CostCodeAssociatedVO> costCodeAssociatedVOS = costCodeAssociatedList.stream().map(
                it -> CostCodeAssociatedVO.builder().id(it.getId())
                        .costService(it.getCostService())
                        .cabinetType(it.getCabinetType())
                        .floatingAmount(it.getFloatingAmount())
                        .costCodeId(it.getCostCodeId()).build()
        ).collect(Collectors.toList());

        CostCodeVO costCodeVO = CostCodeVO.builder()
                .costCodeAssociatedVOs(costCodeAssociatedVOS)
                .id(id)
                .code(costCode.getCode())
                .lastUpdateDate(StringUtils.isEmpty(costCode.getUpdateDate().toString()) ? costCode.getCreatedDate():costCode.getUpdateDate())
                .lastUpdateName(StringUtils.isEmpty(costCode.getUpdateUsername()) ? costCode.getCreatedUsername() :costCode.getUpdateUsername())
                .enabled(costCode.getEnabled()).build();
        return costCodeVO;
    }

    @Override
    public void copy(Integer id) {
        CostCode costCode = get(id);
        List<CostCode> costCodeList = costCodeRepository.findByCodeLike("%"+costCode.getCode()+"%");
        int size = costCodeList.size();
        String[] s = costCodeList.get(size-1).getCode().split("_");
        int num = Integer.parseInt(s[s.length-1])+1;
        CostCode costCode1 = CostCode.builder().code(costCodeList.get(size-1).getCode()+"_"+num)
                .createdUserid(123)
                .createdUsername("344")
                .createdDate(LocalDateTime.now()).build();
        add(costCode1);

        List<CostCodeAssociated> costCodeAssociatedList = costCodeAssociatedRepository.findByCostCodeId(id);
        for (CostCodeAssociated ccad : costCodeAssociatedList) {
            CostCodeAssociated costCodeAssociated = new CostCodeAssociated();
            costCodeAssociated.setCostCodeId(costCode1.getId());
            costCodeAssociated.setCostService(ccad.getCostService());
            costCodeAssociated.setCabinetType(ccad.getCabinetType());
            costCodeAssociated.setFloatingAmount(ccad.getFloatingAmount());
            costCodeAssociatedService.add(costCodeAssociated);
        }
    }

    @Override
    public Page<CostCode> find(CostCodeQueryDTO costCodeQueryDTO, Pageable pageable) {
        QCostCode costCode = QCostCode.costCode;
        BooleanExpression predicate = costCode.deleted.eq('N');
        predicate.and(costCode.enabled.eq('Y'));
        if (StringUtils.isNotEmpty(costCodeQueryDTO.getCode())){
            predicate = costCode.code.like("%" + costCodeQueryDTO.getCode() + "%");
        }
        return costCodeRepository.findAll(predicate,pageable);
    }

    @Override
    public void check(String code) {
        if (StringUtils.isNotEmpty(code)){
            CostCode costCode = costCodeRepository.getByCode(code);
            if(costCode != null) {
                throw new AlreadyExistsException();
            }
        }
    }
}
