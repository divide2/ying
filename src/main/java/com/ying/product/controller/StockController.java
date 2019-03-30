package com.ying.product.controller;

import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.product.dto.InStockDTO;
import com.ying.product.dto.OutStockDTO;
import com.ying.product.model.SpecStock;
import com.ying.product.service.StockService;
import com.ying.product.vo.StockStreamVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
    @PostMapping("/in")
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

    @GetMapping("/warehouse/{warehouseId}")
    @ApiModelProperty("获取某个仓库的库存")
    public ResponseEntity<List<SpecStock>> getByWarehouseId(@PathVariable String warehouseId) {
        return Responser.ok(stockService.getByWarehouseId(warehouseId));
    }

    @GetMapping("/stream")
    @ApiOperation("库存流水")
    public ResponseEntity<Page<StockStreamVO>> findStream(Pageable pageable) {
        Page<StockStreamVO> stockStream = stockService.findStockStream(pageable);
        return Responser.ok(stockStream);
    }


}
