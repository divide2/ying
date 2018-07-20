package com.mj.general.carrier.controller;

import com.mj.core.data.del.SingleId;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.general.carrier.dto.*;
import com.mj.general.carrier.model.Carrier;
import com.mj.general.carrier.service.CarrierService;
import com.mj.general.carrier.vo.CarrierVO;
import com.mj.general.dictionary.dto.EnabledDTO;
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
 * @date 2018/7/9 09:23
 * 船公司管理类
 */
@RestController
@RequestMapping("/v1/carrier")
@Api(tags = "船公司管理")
public class CarrierController {

    private final CarrierService carrierService;

    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @PostMapping
    @ApiOperation("新增船公司")
    public ResponseEntity<Messager> add(@Valid @RequestBody CarrierAddDTO carrierAddDTO, BindingResult br) {
        //todo 获取登陆用户的公司id
        int companyId = 1111;
        Carrier carrier = Carrier.builder().carrierCode(carrierAddDTO.getCarrierCode())
                .carrierCN(carrierAddDTO.getCarrierCN())
                .carrierEN(carrierAddDTO.getCarrierEN())
                .companyId(companyId).build();
        carrierService.add(carrier);
        return Responser.created();
    }

    @PatchMapping
    @ApiOperation("修改船公司")
    public ResponseEntity<Messager> update(@Valid @RequestBody CarrierUpdateDTO carrierUpdateDTO, BindingResult br) {
        Carrier carrier = carrierService.get(carrierUpdateDTO.getId());
        carrier.setCarrierCode(carrierUpdateDTO.getCarrierCode());
        carrier.setCarrierCN(carrierUpdateDTO.getCarrierCN());
        carrier.setCarrierEN(carrierUpdateDTO.getCarrierEN());
        carrierService.update(carrier);
        return Responser.updated();
    }

    @PatchMapping("/enabled")
    @ApiOperation("启用/禁用船公司")
    public ResponseEntity<Messager> enabled(@Valid @RequestBody EnabledDTO enabledDTO, BindingResult br) {
            Carrier carrier = carrierService.get(enabledDTO.getId());
            carrier.setEnabled(enabledDTO.getEnabled());
            carrierService.update(carrier);
            return Responser.updated();
    }

    @GetMapping("/find")
    @ApiOperation("船公司分页查询")
    public ResponseEntity<Page<CarrierVO>> find(CarrierQueryDTO carrierQueryDTO, Pageable pageable) {
        Page<Carrier> carriers = carrierService.find(carrierQueryDTO,pageable);
        Page<CarrierVO> page = carriers.map(carrier -> CarrierVO.builder()
                .id(carrier.getId())
                .carrierCode(carrier.getCarrierCode())
                .carrierCN(carrier.getCarrierCN())
                .carrierEN(carrier.getCarrierEN())
                .enabled(carrier.getEnabled()).build());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/all")
    @ApiOperation("船公司查询")
    public ResponseEntity<List<CarrierVO>> all() {
        List<Carrier> carriers = carrierService.findAll();
        List<CarrierVO> vos = carriers.stream().map(carrier -> CarrierVO.builder()
                .id(carrier.getId())
                .carrierCode(carrier.getCarrierCode())
                .carrierCN(carrier.getCarrierCN())
                .carrierEN(carrier.getCarrierEN())
                .enabled(carrier.getEnabled()).build()).collect(Collectors.toList());
        return ResponseEntity.ok(vos);
    }
    @GetMapping("/check")
    @ApiOperation("检查字段是否重复")
    public void check(CarrierCheckDTO carrierCheckDTO){
        carrierService.check(carrierCheckDTO);
    }

}
