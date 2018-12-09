package com.ying.product.controller;

import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.product.dto.StockDTO;
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
    @PostMapping
    @ApiOperation("入库")
    public ResponseEntity<Messager> add(@Valid @RequestBody StockDTO dto, BindingResult br) {
        stockService.add(dto);
        return Responser.created();
    }

}
