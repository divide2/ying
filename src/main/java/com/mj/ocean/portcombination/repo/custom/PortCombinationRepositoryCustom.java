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

    Page<CombinationVO> findAll(CombinationQueryDTO combinationQueryDTO, Pageable pageable);
}
