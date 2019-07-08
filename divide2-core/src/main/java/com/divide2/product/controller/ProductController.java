package com.divide2.product.controller;

import com.divide2.core.data.del.SingleStringId;
import com.divide2.core.data.resp.Messager;
import com.divide2.core.er.Responser;
import com.divide2.product.dto.ProductDTO;
import com.divide2.product.dto.ProductUpdateDTO;
import com.divide2.product.model.ProductSpec;
import com.divide2.product.service.ProductService;
import com.divide2.product.service.ProductSpecService;
import com.divide2.product.vo.ProductVO;
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
    @ApiOperation("获取产品的规格")
    public ResponseEntity<List<ProductSpec>> listProductSpecs(@PathVariable String productId) {
        List<ProductSpec> info = productSpecService.listByProduct(productId);
        return Responser.ok(info);
    }
}
