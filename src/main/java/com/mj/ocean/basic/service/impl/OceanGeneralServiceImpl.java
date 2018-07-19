package com.mj.ocean.basic.service.impl;

import com.mj.general.carrier.model.Carrier;
import com.mj.general.carrier.service.CarrierService;
import com.mj.general.port.model.Port;
import com.mj.general.port.service.PortService;
import com.mj.ocean.basic.service.OceanGeneralService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@Service
public class OceanGeneralServiceImpl implements OceanGeneralService {
    private final PortService portService;
    private final CarrierService carrierService;

    public OceanGeneralServiceImpl(PortService portService,
                                   CarrierService carrierService) {
        this.portService = portService;
        this.carrierService = carrierService;
    }


    @Override
    public Port getPort(Integer portId) {
        return portService.get(portId);
    }

    @Override
    public Carrier getCarrier(Integer carrierId) {
        return carrierService.get(carrierId);
    }

}
