package com.mj.ocean.surcharge.service;

import com.mj.core.service.BasicService;
import com.mj.ocean.surcharge.dto.SurchargeAddDTO;
import com.mj.ocean.surcharge.dto.SurchargeQueryDTO;
import com.mj.ocean.surcharge.dto.SurchargeUpdateDTO;
import com.mj.ocean.surcharge.model.Surcharge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/18
 */
public interface SurchargeService extends BasicService<Surcharge, Integer> {
    /**
     * 查询
     *
     * @param surchargeQueryDTO dto
     * @param pageable          page
     * @return page
     */
    Page<Surcharge> find(SurchargeQueryDTO surchargeQueryDTO, Pageable pageable);

    /**
     * 切换状态
     *
     * @param id id
     */
    void toggleEnable(Integer id);

    /**
     * 修改
     *
     * @param surchargeUpdateDTO dto
     */
    void update(SurchargeUpdateDTO surchargeUpdateDTO);

    /**
     * 新增
     *
     * @param surchargeAddDTO dto
     */
    void add(SurchargeAddDTO surchargeAddDTO);

    /**
     * 获取具有相同船公司 启运港，目的港的附加费信息
     * @param carrierId 船公司
     * @param pomId 启运港
     * @param podId 目的港
     * @return 附加费
     */
    List<Surcharge> findSameGroup(Integer carrierId, Integer pomId, Integer podId);
}
