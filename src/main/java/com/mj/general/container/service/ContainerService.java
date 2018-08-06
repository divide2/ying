package com.mj.general.container.service;

import com.mj.core.basic.service.BasicService;
import com.mj.general.container.dto.ContainerCheckDTO;
import com.mj.general.container.dto.ContainerQueryDTO;
import com.mj.general.container.model.Container;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zejun
 * @date 2018/7/26 15:42
 */
public interface ContainerService extends BasicService<Container,Integer> {

    /**
     * 切换状态
     * @param id id
     */
    void toggleEnable(Integer id);

    /**
     *  检测字段是否重复
     * @param containerCheckDTO 数据dto
     */
    void check(ContainerCheckDTO containerCheckDTO);

    /**
     * 分页查询柜型数据
     * @param containerQueryDTO 查询dto
     * @param pageable sql数据
     * @return Page<Container>
     */
    Page<Container> find(ContainerQueryDTO containerQueryDTO, Pageable pageable);
}
