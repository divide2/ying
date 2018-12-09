package com.ying.product.controller;

import com.ying.core.data.del.SingleId;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.product.model.Warehouse;
import com.ying.product.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/9
 */


@RestController
@RequestMapping("/v1/warehouse")
@Api(tags = "产品")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @ApiOperation("添加仓库")
    @PostMapping
    public ResponseEntity<Messager> add(@Valid @RequestBody Warehouse dto, BindingResult br) {
        warehouseService.add(dto);
        return Responser.created();
    }

    @PutMapping
    @ApiOperation("修改")
    public ResponseEntity<Messager> update(@Valid @RequestBody Warehouse dto, BindingResult br) {
        warehouseService.update(dto);
        return Responser.updated();
    }

    @ApiOperation("删除")
    @DeleteMapping
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleId id, BindingResult br) {
        warehouseService.delete(id.getId());
        return Responser.deleted();
    }

    @ApiOperation("查一条详情")
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> get(@PathVariable Integer id) {
        Warehouse warehouse = warehouseService.get(id);
        return Responser.ok(warehouse);
    }

    @GetMapping("/find")
    @ApiOperation("获取分页的仓库")
    public ResponseEntity<Page<Warehouse>> find( Pageable pageable) {
        Page<Warehouse> info = warehouseService.find(pageable);
        return Responser.ok(info);
    }

    @GetMapping("/list")
    @ApiOperation("全部仓库")
    public ResponseEntity<List<Warehouse>> list() {
        List<Warehouse> all = warehouseService.findAll();
        return Responser.ok(all);
    }
}
