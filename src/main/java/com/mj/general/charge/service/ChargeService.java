package com.mj.general.charge.service;

import com.mj.core.service.BasicService;
import com.mj.general.carrier.dto.CarrierQueryDTO;
import com.mj.general.charge.dto.ChargeQueryDTO;
import com.mj.general.charge.model.Charge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @auther: zejun
 * @date: 2018/7/10 09:21
 */
public interface ChargeService extends BasicService<Charge,Integer> {

    /**
     * 分页查询
     * @param chargeQueryDTO
     * @param pageable
     * @return
     */
    Page<Charge> find(ChargeQueryDTO chargeQueryDTO, Pageable pageable);
}
