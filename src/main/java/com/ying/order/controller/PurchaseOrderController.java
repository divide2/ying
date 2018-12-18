package com.ying.order.controller;


import com.ying.core.data.del.SingleId;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.order.dto.PurchaseOrderDTO;
import com.ying.order.model.PurchaseOrder;
import com.ying.order.query.PurchaseOrderQuery;
import com.ying.order.service.PurchaseOrderService;
import com.ying.order.vo.PurchaseOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bvvy
 * @date 2018/7/19
 */
@RestController
@RequestMapping("/v1/order/purchase")
@Api(tags = "采购单")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }


    @PatchMapping
    @ApiOperation("修改")
    public ResponseEntity<Messager> update(@Valid @RequestBody PurchaseOrderDTO update, BindingResult br) {
        PurchaseOrder purchaseOrder = purchaseOrderService.get(update.getId());
        purchaseOrderService.update(purchaseOrder);
        return Responser.updated();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleId id, BindingResult br) {
        purchaseOrderService.delete(id.getId());
        return Responser.deleted();
    }

    @GetMapping("/{id}")
    @ApiOperation("获取一条")
    public ResponseEntity<PurchaseOrder> get(@PathVariable Integer id) {
        PurchaseOrder purchaseOrder = purchaseOrderService.get(id);
        return Responser.ok(purchaseOrder);
    }

    @GetMapping("/find")
    @ApiOperation("获取全部")
    public ResponseEntity<Page<PurchaseOrder>> find(PurchaseOrderQuery query, Pageable pageable) {
        Page<PurchaseOrder> orders = purchaseOrderService.find(pageable);
        return Responser.ok(orders);
    }


}
