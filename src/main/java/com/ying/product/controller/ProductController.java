package com.ying.product.controller;

import com.ying.core.data.del.SingleId;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Loginer;
import com.ying.core.er.Responser;
import com.ying.product.dto.ProductAddDTO;
import com.ying.product.dto.ProductUpdateDTO;
import com.ying.product.model.Product;
import com.ying.product.service.ProductService;
import com.ying.product.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bvvy
 * @date 2018/8/16
 */

@RestController
@RequestMapping("/v1/product")
@Api(tags = "作品")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation("添加作品")
    @PostMapping
    public ResponseEntity<Messager> add(@Valid @RequestBody ProductAddDTO dto, BindResult br) {
        productService.add(dto);
        return Responser.created();
    }

    @PutMapping
    @ApiOperation("修改")
    public ResponseEntity<Messager> update(@Valid @RequestBody ProductUpdateDTO dto, BindResult br) {
        productService.update(dto);
        return Responser.updated();
    }

    @ApiOperation("删除")
    @DeleteMapping
    public ResponseEntity<Messager> add(@Valid @RequestBody SingleId id, BindResult br) {
        productService.delete(id.getId());
        return Responser.deleted();
    }

    @ApiOperation("查一条详情")
    @GetMapping("/{id}")
    public ResponseEntity<ProductVO> add(@PathVariable Integer id) {
        Product product = productService.get(id);
        return Responser.ok(ProductVO.of(product));
    }

    @GetMapping("/{userId}/products")
    @ApiOperation("获取单个用户的产品")
    public ResponseEntity<Page<ProductVO>> products(@PathVariable Integer userId, Pageable pageable) {
        Page<Product> products = productService.findByUser(userId,pageable);
        return Responser.ok(products.map(ProductVO::of));
    }

    @GetMapping("/find")
    @ApiOperation("获取单个用户的产品")
    public ResponseEntity<Page<ProductVO>> find( Pageable pageable) {
        Page<Product> products = productService.find(pageable);
        return Responser.ok(products.map(ProductVO::of));
    }

    @GetMapping("/self")
    public ResponseEntity<Page<ProductVO>> findBySelf(Pageable pageable) {
        Page<Product> products = productService.findByUser(Loginer.userId(), pageable);
        return Responser.ok(products.map(ProductVO::of));
    }

}
