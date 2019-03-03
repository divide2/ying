package com.ying.product.controller;

import com.ying.core.data.del.SingleStringId;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.mine.vo.WarehouseVO;
import com.ying.product.dto.WarehouseDTO;
import com.ying.product.dto.WarehouseUpdateDTO;
import com.ying.product.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bvvy
 * @date 2018/12/9
 */


@RestController
@RequestMapping("/v1/warehouse")
@Api(tags = "仓库")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @ApiOperation("添加仓库")
    @PostMapping
    public ResponseEntity<Messager> add(@Valid @RequestBody WarehouseDTO dto, BindingResult br) {
        warehouseService.add(dto);
        return Responser.created();
    }

    @PutMapping
    @ApiOperation("修改")
    public ResponseEntity<Messager> update(@Valid @RequestBody WarehouseUpdateDTO dto, BindingResult br) {
        warehouseService.update(dto);
        return Responser.updated();
    }

    @ApiOperation("删除")
    @DeleteMapping
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleStringId id, BindingResult br) {
        warehouseService.delete(id.getId());
        return Responser.deleted();
    }

    @ApiOperation("查一条详情")
    @GetMapping("/{id}")
    public ResponseEntity<WarehouseVO> get(@PathVariable String id) {
        WarehouseVO warehouse = warehouseService.getVO(id);
        return Responser.ok(warehouse);
    }
}
