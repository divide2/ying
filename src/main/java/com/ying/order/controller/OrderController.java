package com.ying.order.controller;


import com.ying.core.data.del.SingleStringId;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.order.dto.OrderDTO;
import com.ying.order.dto.OrderDeliverDTO;
import com.ying.order.dto.OrderReceiveDTO;
import com.ying.order.model.OrderProductSpec;
import com.ying.order.service.OrderService;
import com.ying.order.service.impl.OrderProductSpecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@RestController
@RequestMapping("/v1/order")
@Api(tags = "订单")
public class OrderController {


    private final OrderService orderService;
    private final OrderProductSpecService orderProductSpecService;

    public OrderController(OrderService orderService,
                           OrderProductSpecService orderProductSpecService) {
        this.orderService = orderService;
        this.orderProductSpecService = orderProductSpecService;
    }

    @PostMapping
    @ApiOperation("添加")
    public ResponseEntity<Messager> add(@Valid @RequestBody OrderDTO dto, BindingResult br) {
        orderService.add(dto);
        return Responser.created();
    }

    @ApiOperation("确认订单")
    @PutMapping("/confirm")
    public ResponseEntity<Messager> confirm(@Valid @RequestBody SingleStringId confirm, BindingResult bindingResult) {
        orderService.confirm(confirm);
        return Responser.updated();
    }

    @ApiModelProperty("确认发货")
    @PutMapping("/confirm/deliver")
    public ResponseEntity<Messager> confirmDeliver(@Valid @RequestBody OrderDeliverDTO deliver, BindingResult bindingResult) {
        orderService.confirmDeliver(deliver);
        return Responser.updated();
    }

    @ApiOperation("确认收货")
    @PutMapping("/confirm/receive")
    public ResponseEntity<Messager> confirmReceive(@Valid @RequestBody OrderReceiveDTO receiveDTO, BindingResult bindingResult) {
        orderService.confirmReceive(receiveDTO);
        return Responser.updated();
    }

    @GetMapping("/{orderId}/spec")
    public ResponseEntity<List<OrderProductSpec>> listOrderProductSpecByOrder(@PathVariable String orderId) {
        List<OrderProductSpec> orderProductSpecs = orderProductSpecService.listByOrder(orderId);
        return Responser.ok(orderProductSpecs);
    }
}
