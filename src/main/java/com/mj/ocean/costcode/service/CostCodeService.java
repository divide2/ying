package com.mj.ocean.costcode.service;

import com.mj.core.service.BasicService;
import com.mj.ocean.costcode.dto.CostCodeAddDTO;
import com.mj.ocean.costcode.dto.CostCodeQueryDTO;
import com.mj.ocean.costcode.dto.CostCodeUpdateDTO;
import com.mj.ocean.costcode.model.CostCode;
import com.mj.ocean.costcode.vo.CostCodeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zejun
 * @date 2018/7/17 10:11
 */
public interface CostCodeService extends BasicService<CostCode,Integer> {

    /**
     * 新增成本代码
     * @param costCodeAddDTO
     */
    void add(CostCodeAddDTO costCodeAddDTO);

    /**
     * 修改成本代码
     * @param costCodeUpdateDTO
     */
    void update(CostCodeUpdateDTO costCodeUpdateDTO);

    /**
     * 查看单条信息
     * @param id
     * @return
     */
    CostCodeVO getDetail(Integer id);

    /**
     * 复制
     * @param id
     */
    void copy(Integer id);

    /**
     * 分页查询
     * @param costCodeQueryDTO
     * @param pageable
     * @return
     */
    Page<CostCode> find(CostCodeQueryDTO costCodeQueryDTO, Pageable pageable);
}
