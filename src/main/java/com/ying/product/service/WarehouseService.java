package com.ying.product.service;

import com.ying.core.basic.service.BasicService;
import com.ying.product.model.Warehouse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/9
 */
public interface WarehouseService extends BasicService<Warehouse, Integer> {


    List<Warehouse> listByTeam(String teamId);
}
