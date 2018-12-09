package com.ying.product.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.product.model.Warehouse;
import com.ying.product.repo.WarehouseRepository;
import com.ying.product.service.WarehouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/9
 */
@Service
public class WarehouseServiceImpl extends SimpleBasicServiceImpl<Warehouse,Integer, WarehouseRepository> implements WarehouseService {

}
