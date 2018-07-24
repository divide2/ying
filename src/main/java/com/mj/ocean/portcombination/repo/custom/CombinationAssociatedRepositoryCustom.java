package com.mj.ocean.portcombination.repo.custom;

import com.mj.ocean.portcombination.vo.CombinationAssociatedVO;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/18 09:40
 */
public interface CombinationAssociatedRepositoryCustom {

    /**
     * 根据组合id查询数据
     * @param combinationId 组合id
     * @return CombinationAssociatedVO
     */
    List<CombinationAssociatedVO> findByCombinationId(Integer combinationId);
}
