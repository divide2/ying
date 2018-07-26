package com.mj.general.container.controller;

import com.mj.core.data.del.SingleId;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.general.container.dto.ContainerAddDTO;
import com.mj.general.container.dto.ContainerCheckDTO;
import com.mj.general.container.dto.ContainerQueryDTO;
import com.mj.general.container.dto.ContainerUpdateDTO;
import com.mj.general.container.model.Container;
import com.mj.general.container.service.ContainerService;
import com.mj.general.container.vo.ContainerVO;
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
 * @date 2018/7/26 15:27
 */
@RestController
@RequestMapping("/v1/container")
@Api(tags = "柜型管理")
public class ContainerController {
    private final ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @PostMapping
    @ApiOperation("新增柜型")
    public ResponseEntity<Messager> add(@Valid @RequestBody ContainerAddDTO containerAddDTO, BindingResult br) {
        Container container = Container.builder()
                .containerCode(containerAddDTO.getContainerCode())
                .containerCN(containerAddDTO.getContainerCN())
                .containerEN(containerAddDTO.getContainerEN()).build();
        containerService.add(container);
        return Responser.created();
    }

    @PatchMapping
    @ApiOperation("修改柜型")
    public ResponseEntity<Messager> update(@Valid @RequestBody ContainerUpdateDTO containerUpdateDTO,BindingResult br) {
        Container container = containerService.get(containerUpdateDTO.getId());
        container.setContainerCode(containerUpdateDTO.getContainerCode());
        container.setContainerCN(containerUpdateDTO.getContainerCN());
        container.setContainerEN(containerUpdateDTO.getContainerEN());
        containerService.update(container);
        return Responser.updated();
    }

    @PatchMapping("/enabled")
    @ApiOperation("启用/禁用")
    public ResponseEntity<Messager> enabled(@Valid @RequestBody SingleId del,BindingResult br) {
        containerService.toggleEnable(del.getId());
        return Responser.updated();
    }

    @GetMapping("/check")
    @ApiOperation("检车字段是否重复")
    public void check(@Valid @RequestBody ContainerCheckDTO containerCheckDTO) {
        containerService.check(containerCheckDTO);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public ResponseEntity<Page<ContainerVO>> find(ContainerQueryDTO containerQueryDTO, Pageable pageable) {
        Page<Container> containers = containerService.find(containerQueryDTO,pageable);
        Page<ContainerVO> page = containers.map(container -> ContainerVO.builder()
                .id(container.getId())
                .containerCode(container.getContainerCode())
                .containerCN(container.getContainerCN())
                .containerEN(container.getContainerEN())
                .enabled(container.getEnabled())
                .build());
        return ResponseEntity.ok(page);
    }

}
