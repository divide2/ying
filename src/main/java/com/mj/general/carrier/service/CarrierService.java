package com.mj.general.carrier.service;

import com.mj.core.basic.service.BasicService;
import com.mj.general.carrier.dto.CarrierCheckDTO;
import com.mj.general.carrier.dto.CarrierQueryDTO;
import com.mj.general.carrier.model.Carrier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author zejun
 * @date 2018/7/8 16:03
 */
public interface CarrierService extends BasicService<Carrier,Integer>{

    /**
     * 分页查询
     * @param carrierQueryDTO
     * @param pageable
     * @return
     */
    Page<Carrier> find(CarrierQueryDTO carrierQueryDTO, Pageable pageable);

    /**
     * 检查字段是否存在
     * @param carrierCheckDTO
     */
    void check(int companyId,CarrierCheckDTO carrierCheckDTO);

    /**
     * 切换状态
     * @param id id
     */
    void toggleEnable(Integer id);
}
