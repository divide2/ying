package com.ying.mine.mine.controller;

import com.ying.basis.star.model.Star;
import com.ying.basis.star.service.StarService;
import com.ying.basis.star.vo.StarVO;
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
@RequestMapping("/v1/mine")
@RestController
public class MineController {

    private final ProductService productService;
    private final StarService starService;

    public MineController(ProductService productService, StarService starService) {
        this.productService = productService;
        this.starService = starService;
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductVO>> product(Pageable pageable) {
            Page<Product> products = productService.findByUser(Loginer.userId(), pageable);
            return Responser.ok(products.map(ProductVO::of));
    }

    @GetMapping("/stars")
    public ResponseEntity<Page<StarVO>> star(Pageable pageable) {
        Page<Star> stars = starService.find(pageable);
        return Responser.ok(stars.map(StarVO::of));
    }
    
}
