package com.mj.ocean.portcombination.service;


import com.mj.core.service.BasicService;
import com.mj.ocean.portcombination.dto.CombinationAddDTO;
import com.mj.ocean.portcombination.dto.CombinationQueryDTO;
import com.mj.ocean.portcombination.dto.CombinationUpdateDTO;
import com.mj.ocean.portcombination.model.PortCombination;
import com.mj.ocean.portcombination.vo.CombinationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zejun
 * @date 2018/7/13 15:42
 */
public interface CombinationService extends BasicService<PortCombination,Integer> {

    Page<CombinationVO> find(CombinationQueryDTO combinationQueryDTO, Pageable pageable);

    void add(CombinationAddDTO combinationAddDTO);

    void update(CombinationUpdateDTO combinationUpdateDTO);
}
