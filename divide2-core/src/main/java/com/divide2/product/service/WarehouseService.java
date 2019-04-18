package com.divide2.product.service;

import com.divide2.core.basic.service.BasicService;
import com.divide2.mine.vo.WarehouseVO;
import com.divide2.product.dto.WarehouseDTO;
import com.divide2.product.dto.WarehouseUpdateDTO;
import com.divide2.product.model.Warehouse;

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
