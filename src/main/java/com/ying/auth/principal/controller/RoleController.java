package com.ying.auth.principal.controller;


import com.ying.auth.acl.service.AclService;
import com.ying.auth.principal.dto.*;
import com.ying.auth.principal.model.Role;
import com.ying.auth.principal.model.User;
import com.ying.auth.principal.service.RoleService;
import com.ying.auth.principal.service.UserService;
import com.ying.auth.principal.vo.RoleVO;
import com.ying.auth.principal.vo.UserVO;
import com.ying.core.data.del.SingleId;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
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
 * @author bvvy
 */
@RequestMapping("/v1/role")
@RestController
@Api(tags = "角色")
public class RoleController {
    private final RoleService roleService;
    private final AclService aclService;
    private final UserService userService;

    public RoleController(RoleService roleService,
                          AclService aclService,
                          UserService userService) {
        this.roleService = roleService;
        this.aclService = aclService;
        this.userService = userService;
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
    public ResponseEntity<Messager> delete(SingleId del) {
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

    @PostMapping("/users")
    @ApiOperation("添加角色用户")
    public ResponseEntity<Messager> addRoleUsers(@Valid @RequestBody RoleAddUsersDTO roleAddUsersDTO, BindingResult br) {
        roleService.addUsers(roleAddUsersDTO);
        return Responser.created();
    }

    @GetMapping("/{roleId}/users")
    @ApiOperation("获取角色所有用户")
    public ResponseEntity<List<UserVO>> findRoleUsers(@PathVariable Integer roleId) {
        List<User> users = userService.findUsersByRole(roleId);
        return Responser.ok(users.stream().map(UserVO::fromUser).collect(Collectors.toList()));
    }

    @PostMapping("/menus")
    @ApiOperation("添加角色权限")
    public ResponseEntity<Messager> addRoleAuth(@Valid @RequestBody RoleAddAuthDTO roleAddAuthDTO, BindingResult br) {
        roleService.addRoleAuth(roleAddAuthDTO);
        return Responser.created();
    }

    @GetMapping("/{roleId}/menus")
    @ApiOperation("获取角色所有菜单id")
    public ResponseEntity<List<Integer>> findRoleMenus(@PathVariable Integer roleId) {
        List<Integer> menuIds = aclService.findMenuIdsByRole(roleId);
        return Responser.ok(menuIds);
    }
}
