package com.mj.general.port.controller;

import com.mj.core.data.del.SingleDelete;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.general.port.dto.PortAddDTO;
import com.mj.general.port.dto.PortQueryDTO;
import com.mj.general.port.dto.PortUpdateDTO;
import com.mj.general.port.model.Port;
import com.mj.general.port.service.PortService;
import com.mj.general.port.vo.PortVO;
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
 * @date: 2018/7/9 17:35
 * 世界港口管理
 */
@RestController
@RequestMapping("/v1/port")
@Api("世界港口")
public class PortController {

    private final PortService portService;

    public PortController(PortService portService) {
        this.portService = portService;
    }

    @PostMapping
    @ApiOperation("新增世界港口")
    public ResponseEntity<Messager> add(@Valid @RequestBody PortAddDTO portAddDTO, BindingResult br) {
        Port port = Port.builder().portCode(portAddDTO.getPortCode())
                .portCN(portAddDTO.getPortCN())
                .portEN(portAddDTO.getPortEN())
                .countryCode(portAddDTO.getCountryCode())
                .countryCN(portAddDTO.getCountryCN())
                .countryEN(portAddDTO.getCountryEN())
                .status(portAddDTO.getStatus())
                .serviceName(portAddDTO.getServiceName()).build();
        portService.add(port);
        return Responser.created();
    }

    @PatchMapping
    @ApiOperation("修改世界港口")
    public ResponseEntity<Messager> update(@Valid @RequestBody PortUpdateDTO portUpdateDTO, BindingResult br) {
        Port port = portService.get(portUpdateDTO.getId());
        port.setPortCode(portUpdateDTO.getPortCode());
        port.setPortCN(portUpdateDTO.getPortCN());
        port.setPortEN(portUpdateDTO.getPortEN());
        port.setCountryCode(portUpdateDTO.getCountryCode());
        port.setCountryCN(portUpdateDTO.getCountryCN());
        port.setCountryEN(portUpdateDTO.getCountryEN());
        port.setServiceName(portUpdateDTO.getServiceName());
        port.setStatus(portUpdateDTO.getStatus());
        portService.update(port);
        return Responser.updated();
    }

    @PatchMapping
    @ApiOperation("删除世界港口")
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleDelete del,BindingResult br) {
        Port port = portService.get(del.getId());
        port.setStatus("1");
        portService.update(port);
        return Responser.deleted();
    }

    @GetMapping("/find")
    @ApiOperation("世界港口分页查询")
    public ResponseEntity<Page<PortVO>> find(PortQueryDTO portQueryDTO, Pageable pageable) {
        Page<Port> ports = portService.find(portQueryDTO,pageable);
        Page<PortVO> page = ports.map(port -> PortVO.builder()
                .id(port.getId())
                .portCode(port.getPortCode())
                .portCN(port.getPortCN())
                .portEN(port.getPortEN())
                .countryCode(port.getCountryCode())
                .countryCN(port.getCountryCN())
                .countryEN(port.getCountryEN())
                .serviceName(port.getServiceName())
                .status(port.getStatus()).build());
        return ResponseEntity.ok(page);
    }

}
