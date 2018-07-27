package com.mj.ocean.portcombination.repo.custom;

import com.mj.ocean.portcombination.dto.CombinationQueryDTO;
import com.mj.ocean.portcombination.model.PortCombination;
import com.mj.ocean.portcombination.vo.CombinationAllVO;
import com.mj.ocean.portcombination.vo.CombinationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    /**
     * 根据船公司id获取所有港口组合
     * @param carrierId 船公司id
     * @return List<PortCombination>
     */
    List<CombinationAllVO> findAllByCarrierId(Integer carrierId);
}
