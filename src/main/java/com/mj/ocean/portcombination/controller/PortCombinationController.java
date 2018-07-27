package com.mj.ocean.portcombination.controller;

import com.mj.core.data.del.SingleId;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.ocean.portcombination.dto.CombinationAddDTO;
import com.mj.ocean.portcombination.dto.CombinationQueryDTO;
import com.mj.ocean.portcombination.dto.CombinationUpdateDTO;
import com.mj.ocean.portcombination.model.PortCombination;
import com.mj.ocean.portcombination.service.CombinationService;
import com.mj.ocean.portcombination.vo.CombinationAllVO;
import com.mj.ocean.portcombination.vo.CombinationAssociatedVO;
import com.mj.ocean.portcombination.vo.CombinationUpdateVO;
import com.mj.ocean.portcombination.vo.CombinationVO;
import com.mj.ocean.portcombination.vo.PortCombinationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public ResponseEntity<Messager> add(@Valid @RequestBody CombinationAddDTO combinationAddDTO, BindingResult br) {
        combinationService.add(combinationAddDTO);
        return Responser.created();
    }

    @PatchMapping
    @ApiOperation("修改港口组")
    public ResponseEntity<Messager> update(@Valid @RequestBody CombinationUpdateDTO combinationUpdateDTO, BindingResult br) {
        combinationService.update(combinationUpdateDTO);
        return Responser.updated();
    }

    @PatchMapping("/enabled")
    @ApiOperation("禁/启用状态")
    public ResponseEntity<Messager> enabled(@Valid @RequestBody SingleId del, BindingResult br) {
        combinationService.toggleEnable(del.getId());
        return Responser.updated();
    }

    @GetMapping("{id}/update")
    @ApiOperation("获取更新时需要的信息")
    public ResponseEntity<CombinationUpdateVO> get(@PathVariable Integer id) {
        return ResponseEntity.ok(combinationService.getUpdateInfo(id));
    }

    @GetMapping("{id}")
    @ApiOperation("获取单个组合信息")
    public ResponseEntity<PortCombinationVO> getUpdateInfo(@PathVariable Integer id) {
        List<CombinationAssociatedVO> combinationAssociatedVOs = combinationService.getDetail(id);
        PortCombination portCombination = combinationService.get(id);
        PortCombinationVO vo = PortCombinationVO.builder()
                .id(portCombination.getId())
                .combinationName(portCombination.getCombinationName())
                .combinationAssociateds(combinationAssociatedVOs).build();
        return ResponseEntity.ok(vo);
    }


    @GetMapping("/find")
    @ApiOperation("港口组合分页查询")
    public ResponseEntity<Page<CombinationVO>> find(CombinationQueryDTO combinationQueryDTO, Pageable pageable) {
        Page<CombinationVO> portCombinations = combinationService.find(combinationQueryDTO, pageable);
        return ResponseEntity.ok(portCombinations);
    }

//    @GetMapping("/copy/{id}")
//    @ApiOperation("复制")
//    public ResponseEntity<CombinationAssociatedVO> copy(@PathVariable Integer id, BindingResult br) {
//        CombinationAssociatedVO combinationAssociatedVO = combinationService.getDetail(id);
//        return Responser.ok(combinationAssociatedVO);
//    }

    @GetMapping("/check/{combinationName}/{}")
    @ApiOperation("检查组合名称是否重复")
    public void check(@PathVariable String combinationName) {
        combinationService.check(combinationName);
    }


    @GetMapping("/getAll")
    @ApiOperation("获取所有港口组合")
    public ResponseEntity<List<CombinationAllVO>> getAll() {
        List<PortCombination> combinations = combinationService.findAll();
        List<CombinationAllVO> combinationAlls = combinations.stream().map(
                pc -> CombinationAllVO.builder()
                        .id(pc.getId())
                        .combinationName(pc.getCombinationName())
                        .build()).collect(Collectors.toList());
        return ResponseEntity.ok(combinationAlls);
    }

    @GetMapping("/getAll/{carrierId}")
    @ApiOperation("根据船公司id获取所有港口组合")
    public ResponseEntity<List<CombinationAllVO>> getAllByCarrierId(@PathVariable Integer carrierId) {
        List<CombinationAllVO> vos = combinationService.findAllByCarrierId(carrierId);
        return ResponseEntity.ok(vos);
    }


}
