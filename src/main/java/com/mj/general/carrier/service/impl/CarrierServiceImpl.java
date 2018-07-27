package com.mj.general.carrier.service.impl;

import com.google.common.collect.Lists;
import com.mj.core.data.properties.StatusProperties;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/8 16:06
 */
@Service
public class CarrierServiceImpl extends SimpleBasicServiceImpl<Carrier,Integer,CarrierRepository> implements CarrierService {

    private final CarrierRepository carrierRepository;
    private final StatusProperties status;

    public CarrierServiceImpl(CarrierRepository carrierRepository,StatusProperties status) {
        this.carrierRepository = carrierRepository;
        this.status = status;
    }

    @Override
    public Page<Carrier> find(CarrierQueryDTO carrierQueryDTO, Pageable pageable) {
        //todo 客户公司id
        int companyId = 1;
        QCarrier carrier = QCarrier.carrier;
        BooleanExpression predicate = Expressions.ONE.eq(Expressions.ONE);
        predicate = predicate.and(carrier.companyId.eq(companyId));
        if(StringUtils.isNotEmpty(carrierQueryDTO.getKeyName())){
            predicate = carrier.carrierCode.like("%" + carrierQueryDTO.getKeyName() + "%")
                    .or(carrier.carrierCN.like("%" + carrierQueryDTO.getKeyName() + "%"))
                    .or(carrier.carrierEN.like("%" + carrierQueryDTO.getKeyName() + "%"));
        }
        return carrierRepository.findAll(predicate, pageable);
    }

    @Override
    public void check(int companyId,CarrierCheckDTO carrierCheckDTO) {
        if(StringUtils.isNotEmpty(carrierCheckDTO.getCarrierCode())){
            Carrier exitCode =  carrierRepository.getByCompanyIdAndCarrierCodeIgnoreCase(companyId,carrierCheckDTO.getCarrierCode());
            if(exitCode != null) {
                throw new GeneralException();
            }
        }
        if(StringUtils.isNotEmpty(carrierCheckDTO.getCarrierCN())){
            Carrier exitCN =  carrierRepository.getByCompanyIdAndCarrierCNIgnoreCase(companyId,carrierCheckDTO.getCarrierCN());
            if(exitCN != null) {
                throw new GeneralException();
            }
        }
        if(StringUtils.isNotEmpty(carrierCheckDTO.getCarrierEN())){
            Carrier exitEN =  carrierRepository.getByCompanyIdAndCarrierENIgnoreCase(companyId,carrierCheckDTO.getCarrierEN());
            if(exitEN != null) {
                throw new GeneralException();
            }
        }
    }

    @Override
    public List<Carrier> findAll() {
        //todo 客户公司id
        int companyId = 1;
        QCarrier carrier = QCarrier.carrier;
        BooleanExpression predicate = carrier.enabled.eq('Y');
        predicate = predicate.and(carrier.companyId.eq(companyId));
        return Lists.newArrayList(carrierRepository.findAll(predicate));
    }

    @Override
    public void toggleEnable(Integer id) {
        Carrier carrier = this.get(id);
        if (status.getEnable().equals(carrier.getEnabled())) {
            carrier.setEnabled(status.getDisable());
        } else {
            carrier.setEnabled(status.getEnable());
        }
        this.update(carrier);
    }
}
