package com.mj.general.port.service.impl;

import com.mj.core.service.impl.SimpleBasicServiceImpl;
import com.mj.general.port.dto.PortQueryDTO;
import com.mj.general.port.model.Port;
import com.mj.general.port.model.QPort;
import com.mj.general.port.repo.PortRepository;
import com.mj.general.port.service.PortService;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @auther: zejun
 * @date: 2018/7/9 17:49
 */
@Service
public class PortServiceImpl extends SimpleBasicServiceImpl<Port,Integer,PortRepository> implements PortService {

    private PortRepository portRepository;

    public PortServiceImpl(PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    @Override
    public Page<Port> find(PortQueryDTO portQueryDTO, Pageable pageable) {
        QPort port = QPort.port;
        BooleanExpression predicate = port.deleted.eq(0);
        predicate.and(port.status.eq("0"));
        if (StringUtils.isNotEmpty(portQueryDTO.getKeyName())){
            predicate = port.portCode.like("%" + portQueryDTO.getKeyName() + "%")
                    .or(port.portCN.like("%" + portQueryDTO.getKeyName() + "%"))
                    .or(port.portEN.like("%" + portQueryDTO.getKeyName() + "%"))
                    .or(port.countryCN.like("%" + portQueryDTO.getKeyName() + "%"))
                    .or(port.countryEN.like("%" + portQueryDTO.getKeyName() + "%"))
                    .or(port.serviceName.like("%" + portQueryDTO.getKeyName() + "%"));
        }
        return portRepository.findAll(predicate,pageable);
    }
}
