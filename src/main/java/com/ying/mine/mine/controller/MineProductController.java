package com.ying.mine.mine.controller;

import com.ying.core.er.Loginer;
import com.ying.core.er.Responser;
import com.ying.product.product.model.Product;
import com.ying.product.product.service.ProductService;
import com.ying.product.product.vo.ProductVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bvvy
 * @date 2018/9/6
 */
@RequestMapping("/v1/mine/product")
@RestController
public class MineProductController {

    private final ProductService productService;

    public MineProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/find")
    public ResponseEntity<Page<ProductVO>> find(Pageable pageable) {
            Page<Product> products = productService.findByUser(Loginer.userId(), pageable);
            return Responser.ok(products.map(ProductVO::of));
    }

    
}
