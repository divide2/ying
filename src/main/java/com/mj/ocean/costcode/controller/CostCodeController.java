package com.mj.ocean.costcode.controller;

import com.mj.core.data.del.SingleId;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.ocean.basic.dto.OceanEnabledDTO;
import com.mj.ocean.costcode.dto.CostCodeAddDTO;
import com.mj.ocean.costcode.dto.CostCodeQueryDTO;
import com.mj.ocean.costcode.dto.CostCodeUpdateDTO;
import com.mj.ocean.costcode.model.CostCode;
import com.mj.ocean.costcode.service.CostCodeAssociatedService;
import com.mj.ocean.costcode.service.CostCodeService;
import com.mj.ocean.costcode.vo.CostCodeAssociatedVO;
import com.mj.ocean.costcode.vo.CostCodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zejun
 * @date 2018/7/17 10:16
 */
@RestController
@RequestMapping("/v1/costcode")
@Api(tags = "成本代码")
public class CostCodeController {

    private final CostCodeService costCodeService;
    private final CostCodeAssociatedService costCodeAssociatedService;

    public CostCodeController(CostCodeService costCodeService,
                              CostCodeAssociatedService costCodeAssociatedService) {
        this.costCodeService = costCodeService;
        this.costCodeAssociatedService = costCodeAssociatedService;
    }

    @PostMapping
    @ApiOperation("新增成本代码")
    public ResponseEntity<Messager> add(@Valid @RequestBody CostCodeAddDTO costCodeAddDTO, BindingResult br){
        costCodeService.add(costCodeAddDTO);
        return Responser.created();
    }

    @PatchMapping
    @ApiOperation("修改成本代码")
    public ResponseEntity<Messager> update(@Valid @RequestBody CostCodeUpdateDTO costCodeUpdateDTO,BindingResult br) {
        costCodeService.update(costCodeUpdateDTO);
        return Responser.created();
    }

    @PatchMapping("/enabled")
    @ApiOperation("启用/禁用状态")
    public ResponseEntity<Messager> enabled(@Valid @RequestBody OceanEnabledDTO oceanEnabledDTO, BindingResult br){
        CostCode costCode = costCodeService.get(oceanEnabledDTO.getId());
        costCode.setEnabled(oceanEnabledDTO.getEnabled());
        costCodeService.update(costCode);
        return Responser.updated();
    }

    @GetMapping("/getOne/{id}")
    @ApiOperation("查看单条信息")
    public ResponseEntity<CostCodeVO> getDetail(@PathVariable Integer id){
        CostCodeVO costCodeVO = costCodeService.getDetail(id);
        return Responser.ok(costCodeVO);
    }

    @PostMapping("/copy")
    @ApiOperation("复制")
    public ResponseEntity<Messager> copy(@Valid @RequestBody SingleId del, BindingResult br) {
        costCodeService.copy(del.getId());
        return Responser.created();
    }

    @GetMapping("/find")
    @ApiOperation("成本代码分页查询")
    public ResponseEntity<Page<CostCodeVO>> find(CostCodeQueryDTO costCodeQueryDTO, Pageable pageable){
        Page<CostCode> costCodes = costCodeService.find(costCodeQueryDTO,pageable);
        Page<CostCodeVO> page = costCodes.map(costCode -> CostCodeVO.builder()
                .id(costCode.getId())
                .code(costCode.getCode())
                .lastUpdateDate(StringUtils.isEmpty(costCode.getUpdateDate().toString()) ? costCode.getCreatedDate():costCode.getUpdateDate())
                .lastUpdateName(StringUtils.isEmpty(costCode.getUpdateUsername()) ? costCode.getCreatedUsername() :costCode.getUpdateUsername())
                .enabled(costCode.getEnabled()).build());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/all")
    @ApiOperation("成本代码查询")
    public ResponseEntity<List<CostCodeVO>> all() {
        List<CostCode> costCodes = costCodeService.findAll();
        List<CostCodeVO> vos = costCodes.stream().map(costCode -> CostCodeVO.builder()
                .id(costCode.getId())
                .code(costCode.getCode()).build()).collect(Collectors.toList());
        return ResponseEntity.ok(vos);
    }

    @GetMapping("/get/{costCodeId}")
    @ApiOperation("根据成本代码id查数据")
    public ResponseEntity<List<CostCodeAssociatedVO>> getAll(@PathVariable Integer costCodeId) {
        List<CostCodeAssociatedVO> vos = costCodeAssociatedService.findByCostCodeId(costCodeId);
        return ResponseEntity.ok(vos);
    }

    @GetMapping("/check")
    @ApiOperation("检查成本代码是否重复")
    public void check(@PathVariable String code) {
        costCodeService.check(code);
    }
}
