package com.mj.general.carrier.service.impl;

import com.google.common.collect.Lists;
import com.mj.core.exception.GeneralException;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.carrier.dto.CarrierCheckDTO;
import com.mj.general.carrier.dto.CarrierQueryDTO;
import com.mj.general.carrier.model.Carrier;
import com.mj.general.carrier.model.QCarrier;
import com.mj.general.carrier.repo.CarrierRepository;
import com.mj.general.carrier.service.CarrierService;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/8 16:06
 */
@Service
public class CarrierServiceImpl extends SimpleBasicServiceImpl<Carrier,Integer,CarrierRepository> implements CarrierService {

    private final CarrierRepository carrierRepository;

    public CarrierServiceImpl(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }

    @Override
    public Page<Carrier> find(CarrierQueryDTO carrierQueryDTO, Pageable pageable) {
        QCarrier carrier = QCarrier.carrier;
        BooleanExpression predicate = Expressions.ONE.eq(Expressions.ONE);
        if(StringUtils.isNotEmpty(carrierQueryDTO.getKeyName())){
            predicate = carrier.carrierCode.like("%" + carrierQueryDTO.getKeyName() + "%")
                    .or(carrier.carrierCN.like("%" + carrierQueryDTO.getKeyName() + "%"))
                    .or(carrier.carrierEN.like("%" + carrierQueryDTO.getKeyName() + "%"));
        }
        return carrierRepository.findAllByOrderByEnabledDesc(predicate, pageable);
    }

    @Override
    public void check(CarrierCheckDTO carrierCheckDTO) {
        if(StringUtils.isNotEmpty(carrierCheckDTO.getCarrierCode())){
            Carrier exitCode =  carrierRepository.getByCarrierCodeIgnoreCase(carrierCheckDTO.getCarrierCode());
            if(exitCode != null) {
                throw new GeneralException();
            }
        }
        if(StringUtils.isNotEmpty(carrierCheckDTO.getCarrierCN())){
            Carrier exitCN =  carrierRepository.getByCarrierCNIgnoreCase(carrierCheckDTO.getCarrierCN());
            if(exitCN != null) {
                throw new GeneralException();
            }
        }
        if(StringUtils.isNotEmpty(carrierCheckDTO.getCarrierEN())){
            Carrier exitEN =  carrierRepository.getByCarrierENIgnoreCase(carrierCheckDTO.getCarrierEN());
            if(exitEN != null) {
                throw new GeneralException();
            }
        }
    }

    @Override
    public List<Carrier> findAll() {
        QCarrier carrier = QCarrier.carrier;
        BooleanExpression predicate = carrier.enabled.eq('Y');
        return Lists.newArrayList(carrierRepository.findAll(predicate));
    }
}
