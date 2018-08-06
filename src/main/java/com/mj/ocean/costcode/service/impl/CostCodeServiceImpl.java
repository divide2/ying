package com.mj.ocean.costcode.service.impl;

import com.mj.core.data.properties.StatusProperties;
import com.mj.core.exception.AlreadyExistsException;
import com.mj.core.basic.service.impl.SimpleBasicServiceImpl;
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
import com.querydsl.core.types.dsl.Expressions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final StatusProperties status;


    public CostCodeServiceImpl(CostCodeRepository costCodeRepository,
                               CostCodeAssociatedService costCodeAssociatedService,
                               CostCodeAssociatedRepository costCodeAssociatedRepository,
                               StatusProperties status) {
        this.costCodeRepository = costCodeRepository;
        this.costCodeAssociatedService = costCodeAssociatedService;
        this.costCodeAssociatedRepository = costCodeAssociatedRepository;
        this.status = status;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CostCodeAddDTO costCodeAddDTO) {
        //todo 根据登陆人信息获取客户公司id，存入数据
        int companyId = 1;
        CostCode costCode = CostCode.builder()
                .code(costCodeAddDTO.getCode())
                .createdUserid(1)
                .createdUsername("admin1")
                .createdDate(LocalDateTime.now())
                .updateUserid(1)
                .updateUsername("admin1")
                .updateDate(LocalDateTime.now())
                .companyId(companyId).build();
        add(costCode);

        int costCodeId = costCode.getId();
        List<CostCodeAssociatedAddDTO> costCodeAssociateds = costCodeAddDTO.getCostCodeAssociateds();
        for (CostCodeAssociatedAddDTO costCodeAssociatedAddDTO : costCodeAssociateds) {
            CostCodeAssociated costCodeAssociated = CostCodeAssociated.builder()
                    .costService(costCodeAssociatedAddDTO.getCostService())
                    .cabinetType(costCodeAssociatedAddDTO.getCabinetType())
                    .floatingAmount(costCodeAssociatedAddDTO.getFloatingAmount())
                    .costCodeId(costCodeId)
                    .companyId(companyId).build();
            costCodeAssociatedService.add(costCodeAssociated);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CostCodeUpdateDTO costCodeUpdateDTO) {
        //todo 根据登陆人信息获取客户公司id，存入数据
        int companyId = 1;
        CostCode costCode = get(costCodeUpdateDTO.getId());
        costCode.setCode(costCodeUpdateDTO.getCode());
        costCode.setUpdateUserid(2);
        costCode.setUpdateUsername("admin2");
        costCode.setUpdateDate(LocalDateTime.now());
        update(costCode);

        //先删除关联表数据，再插入
        costCodeAssociatedRepository.deleteByCostCodeId(costCodeUpdateDTO.getId());

        List<CostCodeAssociatedUpdateDTO> costCodeAssociatedUpdateDTOS = costCodeUpdateDTO.getCostCodeAssociateds();
        for (CostCodeAssociatedUpdateDTO ccaud : costCodeAssociatedUpdateDTOS) {
            CostCodeAssociated costCodeAssociated1 = CostCodeAssociated.builder()
                    .costService(ccaud.getCostService())
                    .cabinetType(ccaud.getCabinetType())
                    .floatingAmount(ccaud.getFloatingAmount())
                    .costCodeId(costCodeUpdateDTO.getId())
                    .companyId(companyId).build();
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
                .costCodeAssociateds(costCodeAssociatedVOS)
                .id(id)
                .code(costCode.getCode())
                .lastUpdateDate(StringUtils.isEmpty(costCode.getUpdateDate().toString()) ? costCode.getCreatedDate():costCode.getUpdateDate())
                .lastUpdateName(StringUtils.isEmpty(costCode.getUpdateUsername()) ? costCode.getCreatedUsername() :costCode.getUpdateUsername())
                .enabled(costCode.getEnabled()).build();
        return costCodeVO;
    }

//    @Override
//    public void copy(Integer id) {
//        CostCode costCode = this.get(id);
//        List<CostCode> costCodeList = costCodeRepository.findByCodeLike("%"+costCode.getCode()+"%");
//        int size = costCodeList.size();
//        String[] s = costCodeList.get(size-1).getCode().split("_");
//        int num = Integer.parseInt(s[s.length-1])+1;
//        CostCode costCode1 = CostCode.builder().code(costCodeList.get(size-1).getCode()+"_"+num)
//                .createdUserid(1)
//                .createdUsername("admin1")
//                .createdDate(LocalDateTime.now()).build();
//        add(costCode1);
//
//        List<CostCodeAssociated> costCodeAssociatedList = costCodeAssociatedRepository.findByCostCodeId(id);
//        for (CostCodeAssociated ccad : costCodeAssociatedList) {
//            CostCodeAssociated costCodeAssociated = new CostCodeAssociated();
//            costCodeAssociated.setCostCodeId(costCode1.getId());
//            costCodeAssociated.setCostService(ccad.getCostService());
//            costCodeAssociated.setCabinetType(ccad.getCabinetType());
//            costCodeAssociated.setFloatingAmount(ccad.getFloatingAmount());
//            costCodeAssociatedService.add(costCodeAssociated);
//        }
//    }

    @Override
    public Page<CostCode> find(CostCodeQueryDTO costCodeQueryDTO, Pageable pageable) {
        //todo 根据登陆人信息获取客户公司id，存入数据
        int companyId = 1;
        QCostCode costCode = QCostCode.costCode;
        BooleanExpression predicate = Expressions.ONE.eq(Expressions.ONE);
        predicate = predicate.and(costCode.companyId.eq(companyId));
        if (StringUtils.isNotEmpty(costCodeQueryDTO.getCode())){
            predicate = costCode.code.like("%" + costCodeQueryDTO.getCode() + "%");
        }
        return costCodeRepository.findAll(predicate,pageable);
    }

    @Override
    public void check(String code) {
        //todo 根据登陆人信息获取客户公司id，存入数据
        int companyId = 1;
        if (StringUtils.isNotEmpty(code)){
            CostCode costCode = costCodeRepository.getByCodeAndCompanyId(code,companyId);
            if(costCode != null) {
                throw new AlreadyExistsException();
            }
        }
    }

    @Override
    public List<CostCode> getAll() {
        //todo 根据登陆人信息获取客户公司id，存入数据
        int companyId = 1;
        return costCodeRepository.findAllByCompanyId(companyId);
    }

    @Override
    public void toggleEnable(Integer id) {
        CostCode costCode = this.get(id);
        if (status.getEnable().equals(costCode.getEnabled())) {
            costCode.setEnabled(status.getDisable());
        } else {
            costCode.setEnabled(status.getEnable());
        }
        this.update(costCode);
    }


}
