package com.ying.mine.controller;

import com.ying.basis.model.Comment;
import com.ying.basis.service.CommentService;
import com.ying.basis.vo.CommentVO;
import com.ying.basis.model.Star;
import com.ying.basis.service.StarService;
import com.ying.basis.vo.StarVO;
import com.ying.core.er.Responser;
import com.ying.friend.vo.FriendVO;
import com.ying.mine.service.MineService;
import com.ying.mine.vo.WarehouseVO;
import com.ying.order.query.OrderQueryParam;
import com.ying.order.vo.OrderVO;
import com.ying.product.query.StockQuery;
import com.ying.product.service.ProductService;
import com.ying.product.vo.ProductVO;
import com.ying.product.vo.StockVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/9/6
 */
@RequestMapping("/v1/mine")
@RestController
@Api(tags = "我的")
public class MineController {

    private final MineService mineService;
    private final ProductService productService;
    private final StarService starService;
    private final CommentService commentService;

    public MineController(MineService mineService, ProductService productService,
                          StarService starService,
                          CommentService commentService) {
        this.mineService = mineService;
        this.productService = productService;
        this.starService = starService;
        this.commentService = commentService;
    }

    @GetMapping("/orders")
    public ResponseEntity<Page<OrderVO>> findOrder(OrderQueryParam queryParam, Pageable pageable) {
        return Responser.ok(mineService.findReceiveOrder(queryParam, pageable));
    }

    @GetMapping("/order/purchase")
    public ResponseEntity<Page<OrderVO>> findPurchaseOrder(OrderQueryParam query, Pageable pageable) {
        Page<OrderVO> vos = mineService.findPurchaseOrder(query, pageable);
        return Responser.ok(vos);
    }

    @GetMapping("/order/sell")
    public ResponseEntity<Page<OrderVO>> findSellOrder(OrderQueryParam query, Pageable pageable) {
        Page<OrderVO> vos = mineService.findSellOrder(query, pageable);
        return Responser.ok(vos);
    }
    @GetMapping("/friends")
    @ApiOperation("我的好友")
    public ResponseEntity<List<FriendVO>> friends() {
        List<FriendVO> vos = mineService.listFriends();
        return Responser.ok(vos);
    }
    @GetMapping("/stocks")
    @ApiOperation("我的库存")
    public ResponseEntity<Page<StockVO>> findStock(Pageable pageable, @Valid StockQuery stockQuery, BindingResult br) {
        Page<StockVO> vo = mineService.findStock(stockQuery, pageable);
        return Responser.ok(vo);
    }
    @GetMapping("/warehouses")
    @ApiOperation("我的仓库")
    public ResponseEntity<List<WarehouseVO>> listWarehouse() {
        List<WarehouseVO> warehouses = mineService.listWarehouse();
        return Responser.ok(warehouses);
    }

    @GetMapping("/products")
    @ApiOperation("我的产品")
    public ResponseEntity<Page<ProductVO>> product(Pageable pageable) {
            Page<ProductVO> products = mineService.findProduct(pageable);
        return Responser.ok(products);
    }

    @GetMapping("/company/products")
    @ApiOperation("我的公司的产品")
    public ResponseEntity<Page<ProductVO>> companyProduct(Pageable pageable) {
        Page<ProductVO> products = mineService.findCompanyProduct(pageable);
        return Responser.ok(products);
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
