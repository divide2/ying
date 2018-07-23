package com.mj.general.charge.service.impl;

import com.mj.core.exception.AlreadyExistsException;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
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

    public ChargeServiceImpl(ChargeRepository chargeRepository) {
        this.chargeRepository = chargeRepository;
    }

    @Override
    public Page<Charge> find(ChargeQueryDTO chargeQueryDTO, Pageable pageable) {
        QCharge charge = QCharge.charge;
        BooleanExpression booleanExpression = Expressions.ONE.eq(Expressions.ONE);
        if (StringUtils.isNotEmpty(chargeQueryDTO.getKeyName())){
            booleanExpression = charge.chargeItemCode.like("%" + chargeQueryDTO.getKeyName() + "%")
                    .or(charge.chargeItemCN.like("%" + chargeQueryDTO.getKeyName() + "%"))
                    .or(charge.chargeItemEN.like("%" + chargeQueryDTO.getKeyName() + "%"));
        }
        return chargeRepository.findAll(booleanExpression,pageable);
    }

    @Override
    public void check(ChargeCheckDTO chargeCheckDTO) {
        if (StringUtils.isNotEmpty(chargeCheckDTO.getChargeItemCode())){
            Charge exitCode = chargeRepository.getByChargeItemCodeIgnoreCase(chargeCheckDTO.getChargeItemCode());
            if(exitCode != null) {
                throw new AlreadyExistsException();
            }
        }
        if (StringUtils.isNotEmpty(chargeCheckDTO.getChargeItemCN())){
            Charge exitCN = chargeRepository.getByChargeItemCNIgnoreCase(chargeCheckDTO.getChargeItemCN());
            if(exitCN != null) {
                throw new AlreadyExistsException();
            }
        }
        if (StringUtils.isNotEmpty(chargeCheckDTO.getChargeItemEN())){
            Charge exitEN = chargeRepository.getByChargeItemENIgnoreCase(chargeCheckDTO.getChargeItemEN());
            if(exitEN != null) {
                throw new AlreadyExistsException();
            }
        }
    }
}
