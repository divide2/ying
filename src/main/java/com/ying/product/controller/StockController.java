package com.ying.product.controller;

import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.product.dto.InStockDTO;
import com.ying.product.dto.OutStockDTO;
import com.ying.product.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author bvvy
 * @date 2018/12/9
 */
@RestController
@RequestMapping("/v1/stock")
@Api(tags = "库存操作")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * instock
     * @param dto
     * @param br
     * @return
     */
    @PostMapping
    @ApiOperation("入库")
    public ResponseEntity<Messager> in(@Valid @RequestBody InStockDTO dto, BindingResult br) {
        stockService.in(dto);
        return Responser.created();
    }

    @PostMapping("/out")
    @ApiOperation("出库")
    public ResponseEntity<Messager> out(@Valid @RequestBody OutStockDTO dto, BindingResult br) {
        stockService.out(dto);
        return Responser.created();
    }



}
