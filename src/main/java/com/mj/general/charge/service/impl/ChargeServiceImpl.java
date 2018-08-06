package com.mj.general.charge.service.impl;

import com.mj.core.data.properties.StatusProperties;
import com.mj.core.exception.AlreadyExistsException;
import com.mj.core.basic.service.impl.SimpleBasicServiceImpl;
import com.mj.general.charge.dto.ChargeCheckDTO;
import com.mj.general.charge.dto.ChargeQueryDTO;
import com.mj.general.charge.model.Charge;
import com.mj.general.charge.model.QCharge;
import com.mj.general.charge.repo.ChargeRepository;
import com.mj.general.charge.service.ChargeService;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author zejun
 * @date 2018/7/10 09:22
 */
@Service
public class ChargeServiceImpl extends SimpleBasicServiceImpl<Charge,Integer,ChargeRepository> implements ChargeService {
    private final ChargeRepository chargeRepository;
    private final StatusProperties status;

    public ChargeServiceImpl(ChargeRepository chargeRepository,StatusProperties status) {
        this.chargeRepository = chargeRepository;
        this.status = status;
    }

    @Override
    public Page<Charge> find(ChargeQueryDTO chargeQueryDTO, Pageable pageable) {
        //todo 获取客户公司id
        int cmpanyId = 1;
        QCharge charge = QCharge.charge;
        BooleanExpression booleanExpression = Expressions.ONE.eq(Expressions.ONE);
        booleanExpression = booleanExpression.and(charge.companyId.eq(cmpanyId));
        if (StringUtils.isNotEmpty(chargeQueryDTO.getKeyName())){
            booleanExpression = charge.chargeItemCode.like("%" + chargeQueryDTO.getKeyName() + "%")
                    .or(charge.chargeItemCN.like("%" + chargeQueryDTO.getKeyName() + "%"))
                    .or(charge.chargeItemEN.like("%" + chargeQueryDTO.getKeyName() + "%"));
        }
        return chargeRepository.findAll(booleanExpression,pageable);
    }

    @Override
    public void check(ChargeCheckDTO chargeCheckDTO) {
        //todo 获取客户公司id
        int companyId = 1;
        if (StringUtils.isNotEmpty(chargeCheckDTO.getChargeItemCode())){
            Charge exitCode = chargeRepository.getByCompanyIdAndChargeItemCodeIgnoreCase(companyId,chargeCheckDTO.getChargeItemCode());
            if(exitCode != null) {
                throw new AlreadyExistsException();
            }
        }
        if (StringUtils.isNotEmpty(chargeCheckDTO.getChargeItemCN())){
            Charge exitCN = chargeRepository.getByCompanyIdAndChargeItemCNIgnoreCase(companyId,chargeCheckDTO.getChargeItemCN());
            if(exitCN != null) {
                throw new AlreadyExistsException();
            }
        }
        if (StringUtils.isNotEmpty(chargeCheckDTO.getChargeItemEN())){
            Charge exitEN = chargeRepository.getByCompanyIdAndChargeItemENIgnoreCase(companyId,chargeCheckDTO.getChargeItemEN());
            if(exitEN != null) {
                throw new AlreadyExistsException();
            }
        }
    }

    @Override
    public void toggleEnable(Integer id) {
        Charge charge = this.get(id);
        if (status.getEnable().equals(charge.getEnabled())) {
            charge.setEnabled(status.getDisable());
        } else {
            charge.setEnabled(status.getEnable());
        }
        this.update(charge);
    }
}
