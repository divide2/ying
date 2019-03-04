package com.ying.product.controller;

import com.ying.core.data.del.SingleStringId;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.product.dto.ProductDTO;
import com.ying.product.dto.ProductUpdateDTO;
import com.ying.product.model.ProductSpec;
import com.ying.product.service.ProductService;
import com.ying.product.service.ProductSpecService;
import com.ying.product.vo.ProductVO;
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
 * @date 2018/8/16
 */

@RestController
@RequestMapping("/v1/product")
@Api(tags = "产品")
public class ProductController {

    private final ProductService productService;
    private final ProductSpecService productSpecService;

    public ProductController(ProductService productService, ProductSpecService productSpecService) {
        this.productService = productService;
        this.productSpecService = productSpecService;
    }

    @ApiOperation("添加产品")
    @PostMapping
    public ResponseEntity<Messager> add(@Valid @RequestBody ProductDTO dto, BindingResult br) {
        productService.add(dto);
        return Responser.created();
    }

    @PutMapping
    @ApiOperation("修改")
    public ResponseEntity<Messager> update(@Valid @RequestBody ProductUpdateDTO dto, BindingResult br) {
        productService.update(dto);
        return Responser.updated();
    }

    @ApiOperation("删除")
    @DeleteMapping
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleStringId id, BindingResult br) {
        productService.delete(id.getId());
        return Responser.deleted();
    }

    @ApiOperation("查一条详情")
    @GetMapping("/{id}")
    public ResponseEntity<ProductVO> add(@PathVariable String id) {
        ProductVO vo = productService.getVO(id);
        return Responser.ok(vo);
    }


    @GetMapping("/find")
    @ApiOperation("获取分页的产品")
    public ResponseEntity<Page<ProductDTO>> find( Pageable pageable) {
        Page<ProductDTO> info = productService.findInfo(pageable);
        return Responser.ok(info);
    }

    @GetMapping("/{productId}/specs")
    @ApiOperation("获取分页的产品")
    public ResponseEntity<List<ProductSpec>> listProductSpecs(@PathVariable String productId) {
        List<ProductSpec> info = productSpecService.listByProduct(productId);
        return Responser.ok(info);
    }
}
