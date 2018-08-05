package com.mj.ocean.quotation.controller;

import com.mj.core.data.del.SingleId;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.ocean.quotation.dto.QuotationAddDTO;
import com.mj.ocean.quotation.dto.QuotationCallHistory;
import com.mj.ocean.quotation.dto.QuotationQueryDTO;
import com.mj.ocean.quotation.dto.QuotationUpdateDTO;
import com.mj.ocean.quotation.service.QuotationService;
import com.mj.ocean.quotation.vo.QuotationInfoVO;
import com.mj.ocean.quotation.vo.QuotationOneVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zejun
 * @date 2018/7/17 18:28
 */
@RestController
@RequestMapping("/v1/quotation")
@Api(tags = "常规/特殊运价")
public class QuotationController {

    private final QuotationService quotationService;

    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @PostMapping
    @ApiOperation("新增常规/特殊报价")
    public ResponseEntity<Messager> add(@Valid @RequestBody QuotationAddDTO quotationAddDTO, BindingResult br) {
        quotationService.add(quotationAddDTO);
        return Responser.created();
    }

    @PatchMapping("/enabled")
    @ApiOperation("禁用状态")
    public ResponseEntity<Messager> enabled(@Valid @RequestBody SingleId del, BindingResult br) {
        quotationService.toggleEnable(del.getId());
        return Responser.updated();
    }

    @GetMapping("/{id}")
    @ApiOperation("获取单条信息")
    public ResponseEntity<QuotationOneVO> get(@PathVariable Integer id) {
        QuotationOneVO quotationOne = quotationService.getOne(id);
        return ResponseEntity.ok(quotationOne);
    }

    @PatchMapping
    @ApiOperation("修改常规/特殊报价")
    public ResponseEntity<Messager> update(@Valid @RequestBody QuotationUpdateDTO quotationUpdateDTO, BindingResult br) {
        quotationService.update(quotationUpdateDTO);
        return Responser.updated();
    }

    @PostMapping("/batch")
    @ApiOperation("批量新增")
    public ResponseEntity<Messager> addBatch(@Valid @RequestBody List<QuotationAddDTO> quotationAddDTOS, BindingResult br) {
        quotationService.addBacth(quotationAddDTOS);
        return Responser.created();
    }

    @GetMapping("/find/{costServiceCode}")
    @ApiOperation("分页查询")
    public ResponseEntity<Page<QuotationOneVO>> find(@PathVariable String costServiceCode, QuotationQueryDTO quotationQueryDTO, Pageable pageable){
        Page<QuotationOneVO> quotations = quotationService.find(costServiceCode,quotationQueryDTO,pageable);
        return ResponseEntity.ok(quotations);
    }

    @GetMapping("/history")
    @ApiOperation("调用历史记录")
    public ResponseEntity<List<QuotationInfoVO>> callHistory(QuotationCallHistory quotationCallHistory,BindingResult br) {
        List<QuotationInfoVO> vos = quotationService.callHistory(quotationCallHistory);
        return ResponseEntity.ok(vos);
    }
}
