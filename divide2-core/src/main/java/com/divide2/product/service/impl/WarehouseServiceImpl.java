package com.divide2.product.service.impl;

import com.divide2.core.basic.service.impl.SimpleBasicServiceImpl;
import com.divide2.core.root.converter.Converter;
import com.divide2.mine.vo.WarehouseVO;
import com.divide2.product.dto.WarehouseDTO;
import com.divide2.product.dto.WarehouseUpdateDTO;
import com.divide2.product.model.Warehouse;
import com.divide2.product.repo.WarehouseRepository;
import com.divide2.product.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/9
 */
@Service
public class WarehouseServiceImpl extends SimpleBasicServiceImpl<Warehouse, String, WarehouseRepository> implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<WarehouseVO> listByTeam(String teamId) {
        return Converter.of(warehouseRepository.findByTeamIdAndEnabled(teamId, true)).convert(this::toVO);
    }

    private WarehouseVO toVO(Warehouse warehouse) {
        WarehouseVO vo = new WarehouseVO();
        vo.setId(warehouse.getId());
        vo.setName(warehouse.getName());
        vo.setRemarks(warehouse.getRemarks());
        vo.setTeamId(warehouse.getTeamId());
        return vo;
    }

    @Override
    public void delete(String id) {
        Warehouse warehouse = this.get(id);
        warehouse.setEnabled(false);
        this.update(warehouse);
    }

    @Override
    public void add(WarehouseDTO dto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(dto.getName());
        warehouse.setTeamId(dto.getTeamId());
        warehouse.setRemarks(dto.getRemarks());
        warehouse.setEnabled(true);
        this.add(warehouse);
    }

    @Override
    public void update(WarehouseUpdateDTO dto) {
        Warehouse warehouse = this.get(dto.getId());
        warehouse.setName(dto.getName());
        warehouse.setRemarks(dto.getRemarks());
        this.update(warehouse);
    }

    @Override
    public WarehouseVO getVO(String id) {
        Warehouse warehouse = this.get(id);
        return toVO(warehouse);
    }


}
