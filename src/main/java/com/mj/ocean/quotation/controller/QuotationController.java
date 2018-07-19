package com.mj.ocean.quotation.controller;

import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.ocean.quotation.dto.*;
import com.mj.ocean.quotation.model.Quotation;
import com.mj.ocean.quotation.service.QuotationService;
import com.mj.ocean.quotation.vo.QuotationVO;
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
 * @author zejun
 * @date 2018/7/17 18:28
 */
@RestController
@RequestMapping("/v1/quotation")
@Api(tags = "常规运价")
public class QuotationController {

    private final QuotationService quotationService;

    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @PostMapping
    @ApiOperation("新增常规报价")
    public ResponseEntity<Messager> add(@Valid @RequestBody QuotationAddDTO quotationAddDTO, BindingResult br) {
        quotationService.add(quotationAddDTO);
        return Responser.created();
    }

    @PatchMapping("/stats")
    @ApiOperation("禁用状态")
    public ResponseEntity<Messager> enabled(@Valid @RequestBody QuotationEnabledDTO quotationEnabledDTO,BindingResult br) {
        Quotation quotation = quotationService.get(quotationEnabledDTO.getId());
        quotation.setEnabled(quotationEnabledDTO.getEnabled());
        quotationService.update(quotation);
        return Responser.updated();
    }

    @GetMapping("/get/{id}")
    @ApiModelProperty("获取单条信息")
    public ResponseEntity<QuotationVO> get(@PathVariable Integer id) {
        QuotationVO quotationVO = quotationService.getOne(id);
        return ResponseEntity.ok(quotationVO);
    }

    @PatchMapping
    @ApiOperation("修改常规报价")
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
    public ResponseEntity<Page<QuotationVO>> find(@PathVariable String costServiceCode, QuotationQueryDTO quotationQueryDTO, Pageable pageable){
        Page<QuotationVO> quotations = quotationService.find(costServiceCode,quotationQueryDTO,pageable);
        return ResponseEntity.ok(quotations);
    }

    @GetMapping("/history")
    @ApiOperation("调用历史记录")
    public ResponseEntity<List<QuotationVO>> callHistory(QuotationCallHistory quotationCallHistory,BindingResult br) {
        List<QuotationVO> vos = quotationService.callHistory(quotationCallHistory);
        return ResponseEntity.ok(vos);
    }
}
