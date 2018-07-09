package com.mj.auth.principal.controller;


import com.mj.auth.acl.service.AclService;
import com.mj.auth.principal.dto.RoleAddDTO;
import com.mj.auth.principal.dto.RolePerAddDTO;
import com.mj.auth.principal.dto.RoleQueryDTO;
import com.mj.auth.principal.dto.RoleUpdateDTO;
import com.mj.auth.principal.model.Role;
import com.mj.auth.principal.service.RoleService;
import com.mj.auth.principal.vo.RoleVO;
import com.mj.core.data.del.SingleDelete;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author bvvy
 */
@RequestMapping("/v1/role")
@RestController
@Api(tags = "角色")
public class RoleController {
    private final RoleService roleService;
    private final AclService aclService;

    public RoleController(RoleService roleService, AclService aclService) {
        this.roleService = roleService;
        this.aclService = aclService;
    }

    @PostMapping
    @ApiOperation("添加角色")
    public ResponseEntity<Messager>  add(@Valid @RequestBody RoleAddDTO roleAddDTO, BindingResult br) {
        Role role = Role.builder()
                .code(roleAddDTO.getCode())
                .name(roleAddDTO.getName()).build();
        roleService.add(role);
        return Responser.created();
    }

    @PatchMapping
    @ApiOperation("修改角色")
    public ResponseEntity<Messager> update(@Valid @RequestBody RoleUpdateDTO roleUpdateDTO, BindingResult br) {
        Role role = roleService.get(roleUpdateDTO.getId());
        role.setCode(roleUpdateDTO.getCode());
        role.setName(roleUpdateDTO.getName());
        roleService.update(role);
        return Responser.updated();
    }

    @DeleteMapping
    @ApiOperation("删除角色")
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleDelete del,BindingResult br) {
        roleService.delete(del.getId());
        return Responser.deleted();
    }


    @GetMapping("/{id}")
    @ApiOperation("获取单个角色")
    public ResponseEntity<RoleVO> get(@PathVariable Integer id) {
        Role role = roleService.get(id);
        RoleVO roleVO =
                RoleVO.builder().code(role.getCode()).name(role.getName()).id(role.getId()).build();
        return Responser.ok(roleVO);
    }

    /**
     * 单表模糊查询
     * @param query 条件
     * @param pageable pageable
     * @return result
     */
    @GetMapping("/find")
    public ResponseEntity<Page<RoleVO>> find(RoleQueryDTO query, Pageable pageable) {
        Page<Role> roles = roleService.find(query, pageable);
        Page<RoleVO> page = roles.map(role -> RoleVO.builder()
                .code(role.getCode())
                .name(role.getName())
                .id(role.getId())
                .build());
        return ResponseEntity.ok(page);
    }

    @PostMapping("/authorize")
    @ApiOperation("给角色授权")
    public ResponseEntity<Messager> authorize(@Valid @RequestBody RolePerAddDTO rolePerAddDTO, BindingResult br) {
        aclService.addRolePerm(rolePerAddDTO);
        return Responser.created();
    }
}
