package com.mj.general.carrier.service.impl;

import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.carrier.dto.CarrierQueryDTO;
import com.mj.general.carrier.model.Carrier;
import com.mj.general.carrier.model.QCarrier;
import com.mj.general.carrier.repo.CarrierRepository;
import com.mj.general.carrier.service.CarrierService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



/**
 * @auther: zejun
 * @date: 2018/7/8 16:06
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
        BooleanExpression predicate = carrier.deleted.eq(0);
        predicate.and(carrier.status.eq("0"));
        if(StringUtils.isNotEmpty(carrierQueryDTO.getKeyName())){
            predicate = carrier.carrierCode.like("%" + carrierQueryDTO.getKeyName() + "%")
                    .or(carrier.carrierCN.like("%" + carrierQueryDTO.getKeyName() + "%"))
                    .or(carrier.carrierEN.like("%" + carrierQueryDTO.getKeyName() + "%"));
        }
        return carrierRepository.findAll(predicate,pageable);
    }
}
