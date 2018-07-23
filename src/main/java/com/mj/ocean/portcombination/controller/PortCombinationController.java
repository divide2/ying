package com.mj.ocean.portcombination.controller;

import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.ocean.basic.dto.OceanEnabledDTO;
import com.mj.ocean.portcombination.dto.CombinationAddDTO;
import com.mj.ocean.portcombination.dto.CombinationQueryDTO;
import com.mj.ocean.portcombination.dto.CombinationUpdateDTO;
import com.mj.ocean.portcombination.model.PortCombination;
import com.mj.ocean.portcombination.service.CombinationService;
import com.mj.ocean.portcombination.vo.CombinationAssociatedVO;
import com.mj.ocean.portcombination.vo.CombinationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zejun
 * @date 2018/7/13 15:44
 */
@RestController
@RequestMapping("/v1/port/combination")
@Api(tags = "港口组合")
public class PortCombinationController {

    private final CombinationService combinationService;

    public PortCombinationController(CombinationService combinationService) {
        this.combinationService = combinationService;
    }

    @PostMapping
    @ApiOperation("新增港口组")
    public ResponseEntity<Messager> add(@Valid @RequestBody CombinationAddDTO combinationAddDTO, BindingResult br){
        combinationService.add(combinationAddDTO);
        return Responser.created();
    }

    @PatchMapping
    @ApiOperation("修改港口组")
    public ResponseEntity<Messager> update(@Valid @RequestBody CombinationUpdateDTO combinationUpdateDTO,BindingResult br) {
        combinationService.update(combinationUpdateDTO);
        return Responser.updated();
    }

    @PatchMapping("/enabled")
    @ApiOperation("禁/启用状态")
    public ResponseEntity<Messager> enabled(@Valid @RequestBody OceanEnabledDTO oceanEnabledDTO, BindingResult br){
        PortCombination portCombination = combinationService.get(oceanEnabledDTO.getId());
        portCombination.setEnabled(oceanEnabledDTO.getEnabled());
        combinationService.update(portCombination);
        return  Responser.updated();
    }

    @GetMapping("/find")
    @ApiOperation("港口组合分页查询")
    public ResponseEntity<Page<CombinationVO>> find(CombinationQueryDTO combinationQueryDTO, Pageable pageable){
        Page<CombinationVO> portCombinations = combinationService.find(combinationQueryDTO,pageable);
        return ResponseEntity.ok(portCombinations);
    }

    @GetMapping("/copy/{id}")
    @ApiOperation("复制")
    public ResponseEntity<CombinationAssociatedVO> copy(@PathVariable Integer id, BindingResult br) {
        CombinationAssociatedVO combinationAssociatedVO = combinationService.getDetail(id);
        return Responser.ok(combinationAssociatedVO);
    }
}
