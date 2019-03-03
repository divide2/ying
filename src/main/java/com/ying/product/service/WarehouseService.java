package com.ying.product.service;

import com.ying.core.basic.service.BasicService;
import com.ying.mine.vo.WarehouseVO;
import com.ying.product.dto.WarehouseDTO;
import com.ying.product.dto.WarehouseUpdateDTO;
import com.ying.product.model.Warehouse;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/9
 */
public interface WarehouseService extends BasicService<Warehouse, String> {


    List<WarehouseVO> listByTeam(String teamId);

    void add(WarehouseDTO dto);

    void update(WarehouseUpdateDTO dto);

    WarehouseVO getVO(String id);
}
