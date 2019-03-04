package com.ying.product.controller;

import com.ying.core.data.del.SingleStringId;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.product.model.ProductSpec;
import com.ying.product.service.ProductSpecService;
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
 * @date 2018/12/10
 */
@RestController
@Api
@RequestMapping("/v1/product/spec")
public class ProductSpecController {

    private final ProductSpecService productSpecService;

    public ProductSpecController(ProductSpecService productSpecService) {
        this.productSpecService = productSpecService;
    }

    @ApiOperation("添加一个规格")
    @PostMapping
    public ResponseEntity<Messager> add(@Valid @RequestBody ProductSpec dto, BindingResult br) {
        productSpecService.add(dto);
        return Responser.created();
    }

    @PutMapping
    @ApiOperation("修改")
    public ResponseEntity<Messager> update(@Valid @RequestBody ProductSpec dto, BindingResult br) {
        productSpecService.update(dto);
        return Responser.updated();
    }

    @ApiOperation("删除")
    @DeleteMapping
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleStringId id, BindingResult br) {
        productSpecService.delete(id.getId());
        return Responser.deleted();
    }

    @ApiOperation("查一条详情")
    @GetMapping("/{id}")
    public ResponseEntity<ProductSpec> get(@PathVariable String id) {
        ProductSpec warehouse = productSpecService.get(id);
        return Responser.ok(warehouse);
    }

    @GetMapping("/find")
    @ApiOperation("获取分页的仓库")
    public ResponseEntity<Page<ProductSpec>> find(Pageable pageable) {
        Page<ProductSpec> info = productSpecService.find(pageable);
        return Responser.ok(info);
    }

    @GetMapping("/list")
    @ApiOperation("全部仓库")
    public ResponseEntity<List<ProductSpec>> list() {
        List<ProductSpec> all = productSpecService.findAll();
        return Responser.ok(all);
    }
}
