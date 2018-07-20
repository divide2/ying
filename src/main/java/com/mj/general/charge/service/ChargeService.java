package com.mj.general.charge.service;

import com.mj.core.service.BasicService;
import com.mj.general.carrier.dto.CarrierQueryDTO;
import com.mj.general.charge.dto.ChargeAddDTO;
import com.mj.general.charge.dto.ChargeCheckDTO;
import com.mj.general.charge.dto.ChargeQueryDTO;
import com.mj.general.charge.model.Charge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zejun
 * @date 2018/7/10 09:21
 */
public interface ChargeService extends BasicService<Charge,Integer> {

    /**
     * 分页查询
     * @param chargeQueryDTO 查询dto
     * @param pageable 分页数据
     * @return Page<Charge>
     */
    Page<Charge> find(ChargeQueryDTO chargeQueryDTO, Pageable pageable);

    /**
     * 检查字段是否存在
     * @param chargeCheckDTO 检车dto
     */
    void check(ChargeCheckDTO chargeCheckDTO);
}
