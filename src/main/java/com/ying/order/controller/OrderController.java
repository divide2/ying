package com.ying.order.controller;


import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.order.dto.OrderConfirmDTO;
import com.ying.order.dto.OrderDTO;
import com.ying.order.service.OrderService;
import com.ying.order.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@RestController
@RequestMapping("/v1/order")
@Api(tags = "订单")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ApiOperation("添加")
    public ResponseEntity<Messager> add(@Valid @RequestBody OrderDTO dto, BindingResult br) {
        orderService.add(dto);
        return Responser.created();
    }

    @PutMapping("/confirm")
    public ResponseEntity<Messager> confirm(@Valid @RequestBody OrderConfirmDTO confirm, BindingResult bindingResult) {
        orderService.confirm(confirm);
        return Responser.updated();
    }
}
