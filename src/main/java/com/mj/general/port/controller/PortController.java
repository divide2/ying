package com.mj.general.port.controller;

import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.general.dictionary.dto.GeneralEnabledDTO;
import com.mj.general.port.dto.PortAddDTO;
import com.mj.general.port.dto.PortCheckDTO;
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
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author zejun
 * @date 2018/7/9 17:35
 * 世界港口管理
 */
@RestController
@RequestMapping("/v1/port")
@Api(tags = "世界港口")
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
                .enabled(portAddDTO.getEnabled())
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
        port.setEnabled(portUpdateDTO.getEnabled());
        portService.update(port);
        return Responser.updated();
    }

    @PatchMapping("/enabled")
    @ApiOperation("启用/禁用状态")
    public ResponseEntity<Messager> enabled(@Valid @RequestBody GeneralEnabledDTO generalEnabledDTO, BindingResult br) {
        Port port = portService.get(generalEnabledDTO.getId());
        port.setEnabled(generalEnabledDTO.getEnabled());
        portService.update(port);
        return Responser.updated();
    }

    @GetMapping("/find")
    @ApiOperation("世界港口分页查询")
    public ResponseEntity<Page<PortVO>> find(PortQueryDTO portQueryDTO, Pageable pageable) {
        Page<Port> ports = portService.find(portQueryDTO, pageable);
        Page<PortVO> page = ports.map(PortVO::fromPort);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/all")
    @ApiOperation("全部港口")
    public ResponseEntity<List<PortVO>> all() {
        List<Port> all = portService.findAll();
        List<PortVO> vos = all.stream().map(PortVO::fromPort).collect(toList());
        return Responser.ok(vos);
    }

    @GetMapping("/check")
    @ApiOperation("检查字段是否重复")
    public void check(PortCheckDTO portCheckDTO) {
        portService.check(portCheckDTO);
    }
}
