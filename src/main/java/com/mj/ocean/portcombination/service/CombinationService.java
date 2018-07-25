package com.mj.ocean.portcombination.service;


import com.mj.core.service.BasicService;
import com.mj.ocean.portcombination.dto.CombinationAddDTO;
import com.mj.ocean.portcombination.dto.CombinationQueryDTO;
import com.mj.ocean.portcombination.dto.CombinationUpdateDTO;
import com.mj.ocean.portcombination.model.PortCombination;
import com.mj.ocean.portcombination.vo.CombinationAssociatedVO;
import com.mj.ocean.portcombination.vo.CombinationUpdateVO;
import com.mj.ocean.portcombination.vo.CombinationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/13 15:42
 */
public interface CombinationService extends BasicService<PortCombination,Integer> {

    /**
     * 获取分页查询信息
     * @param combinationQueryDTO 查询dto
     * @param pageable 分页sql数据
     * @return Page<CombinationVO>
     */
    Page<CombinationVO> find(CombinationQueryDTO combinationQueryDTO, Pageable pageable);

    /**
     * 新增港口组合数据
     * @param combinationAddDTO 新增dto
     */
    void add(CombinationAddDTO combinationAddDTO);

    /**
     * 修改港口组合数据
     * @param combinationUpdateDTO 修改dto
     */
    void update(CombinationUpdateDTO combinationUpdateDTO);

    /**
     * 获取单条信息
     * @param id 组合id
     * @return CombinationAssociatedVO
     */
    List<CombinationAssociatedVO> getDetail(Integer id);

    /**
     * 检查组合名称是否重复
     * @param combinationName 组合名称
     */
    void check(String combinationName);

    /**
     * 切换状态
     * @param id id
     */
    void toggleEnable(Integer id);

    /**
     * 获取更新时需要的信息
     * @param id id
     * @return vo
     */
    CombinationUpdateVO getUpdateInfo(Integer id);
}

