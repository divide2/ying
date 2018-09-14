package com.ying.mine.mine.controller;

import com.ying.basis.comment.model.Comment;
import com.ying.basis.comment.service.CommentService;
import com.ying.basis.comment.vo.CommentVO;
import com.ying.basis.star.model.Star;
import com.ying.basis.star.service.StarService;
import com.ying.basis.star.vo.StarVO;
import com.ying.core.er.Loginer;
import com.ying.core.er.Responser;
import com.ying.product.product.model.Product;
import com.ying.product.product.service.ProductService;
import com.ying.product.product.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "我的")
public class MineController {

    private final ProductService productService;
    private final StarService starService;
    private final CommentService commentService;

    public MineController(ProductService productService,
                          StarService starService,
                          CommentService commentService) {
        this.productService = productService;
        this.starService = starService;
        this.commentService = commentService;
    }

    @GetMapping("/products")
    @ApiOperation("我的产品")
    public ResponseEntity<Page<ProductVO>> product(Pageable pageable) {
            Page<Product> products = productService.findByUser(Loginer.userId(), pageable);
            return Responser.ok(products.map(ProductVO::of));
    }

    @GetMapping("/stars")
    @ApiOperation("我的star")
    public ResponseEntity<Page<StarVO>> star(Pageable pageable) {
        Page<Star> stars = starService.findByUser(pageable);
        return Responser.ok(stars.map(StarVO::of));
    }

    @GetMapping("/comments")
    @ApiOperation("我的评论")
    public ResponseEntity<Page<CommentVO>> comments(Pageable pageable) {
        Page<Comment> comments = commentService.findByUser(pageable);
        return Responser.ok(comments.map(CommentVO::of));
    }
}
