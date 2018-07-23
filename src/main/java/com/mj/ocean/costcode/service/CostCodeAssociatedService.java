package com.mj.ocean.costcode.service;

import com.mj.core.service.BasicService;
import com.mj.ocean.costcode.model.CostCodeAssociated;
import com.mj.ocean.costcode.vo.CostCodeAssociatedVO;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/17 10:20
 */
public interface CostCodeAssociatedService extends BasicService<CostCodeAssociated,Integer> {

    /**
     * 根据成本代码id获取数据
     * @param costCodeId  成本代码id
     * @return List<CostCodeAssociatedVO>
     */
    List<CostCodeAssociatedVO> findByCostCodeId(Integer costCodeId);
}
