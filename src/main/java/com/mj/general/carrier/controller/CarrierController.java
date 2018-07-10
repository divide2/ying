package com.mj.general.carrier.controller;

import com.mj.core.data.del.SingleDelete;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.general.carrier.dto.CarrierAddDTO;
import com.mj.general.carrier.dto.CarrierQueryDTO;
import com.mj.general.carrier.dto.CarrierUpdateDTO;
import com.mj.general.carrier.model.Carrier;
import com.mj.general.carrier.service.CarrierService;
import com.mj.general.carrier.vo.CarrierVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auther: zejun
 * @date: 2018/7/9 09:23
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

    @PostMapping("/add")
    @ApiOperation("新增船公司")
    public ResponseEntity<Messager> add(@Valid @RequestBody CarrierAddDTO carrierAddDTO, BindingResult br) {
        Carrier carrier = Carrier.builder().carrierCode(carrierAddDTO.getCarrierCode())
                .carrierCN(carrierAddDTO.getCarrierCN())
                .carrierEN(carrierAddDTO.getCarrierEN()).build();
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

    @PatchMapping
    @ApiOperation("删除船公司")
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleDelete del,BindingResult br) {
        Carrier carrier = carrierService.get(del.getId());
        carrier.setStatus("1");
        carrierService.update(carrier);
        return Responser.deleted();
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
                .status(carrier.getStatus()).build());
        return ResponseEntity.ok(page);
    }
}
