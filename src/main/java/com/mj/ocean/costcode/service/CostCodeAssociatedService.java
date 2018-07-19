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

    List<CostCodeAssociatedVO> findByCostCodeId(Integer costCodeId);
}
