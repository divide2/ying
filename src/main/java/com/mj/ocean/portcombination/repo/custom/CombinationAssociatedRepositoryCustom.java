package com.mj.ocean.portcombination.repo.custom;

import com.mj.ocean.portcombination.vo.CombinationAssociatedVO;

/**
 * @author zejun
 * @date 2018/7/18 09:40
 */
public interface CombinationAssociatedRepositoryCustom {

    CombinationAssociatedVO findByCombinationId(Integer combinationId);
}
