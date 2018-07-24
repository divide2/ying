package com.mj.ocean.portcombination.repo.custom;

import com.mj.ocean.portcombination.dto.CombinationQueryDTO;
import com.mj.ocean.portcombination.vo.CombinationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zejun
 * @date 2018/7/16 09:43
 */
public interface PortCombinationRepositoryCustom {

    /**
     * 分页查询港口组合数据
     * @param combinationQueryDTO 查询dto
     * @param pageable sql数据
     * @return Page<CombinationVO>
     */
    Page<CombinationVO> findAll(Integer companyId,CombinationQueryDTO combinationQueryDTO, Pageable pageable);
}
