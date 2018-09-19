package com.ying.auth.res.controller;

import com.ying.auth.res.dto.MenuAddDTO;
import com.ying.auth.res.dto.MenuUpdateDTO;
import com.ying.auth.res.model.Menu;
import com.ying.auth.res.payload.MenuPayload;
import com.ying.auth.res.service.MenuService;
import com.ying.auth.res.service.OperService;
import com.ying.auth.res.vo.MenuVO;
import com.ying.core.data.del.SingleId;
import com.ying.core.data.resp.Messager;
import com.ying.core.data.tree.Tree;
import com.ying.core.er.Responser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author bvvy
 * 菜单contorller
 */
@RestController
@RequestMapping("/v1/menu")
@Api(tags = "菜单")
public class MenuController {

    private final MenuService menuService;
    private final OperService operService;

    public MenuController(MenuService menuService,
                          OperService operService) {
        this.menuService = menuService;
        this.operService = operService;

    }

    @PostMapping
    @ApiOperation("添加")
    public ResponseEntity<Messager> add(@Valid @RequestBody MenuAddDTO menuAddDTO, BindingResult br) {
        menuService.add(menuAddDTO);
        return Responser.created();
    }

    @PatchMapping
    @ApiOperation("修改")
    public ResponseEntity<Messager> update(@Valid @RequestBody MenuUpdateDTO menuUpdateDTO, BindingResult br) {
        Menu menu = menuService.get(menuUpdateDTO.getId());
        menu.setEnabled(menuUpdateDTO.getEnabled());
        menu.setName(menuUpdateDTO.getName());
        menu.setPath(menuUpdateDTO.getPath());
        menu.setOrderNum(menuUpdateDTO.getOrderNum());
        menu.setPid(menuUpdateDTO.getPid());
        menu.setType(menuUpdateDTO.getType());
        menu.setCode(menuUpdateDTO.getCode());
        menuService.update(menu);
        return Responser.updated();
    }

    @GetMapping("/{id}")
    @ApiOperation("获取单个")
    public ResponseEntity<MenuVO> get(@PathVariable Integer id) {
        Menu menu = menuService.get(id);
        MenuVO menuVO = MenuVO.builder()
                .enabled(menu.getEnabled())
                .id(menu.getId())
                .name(menu.getName())
                .orderNum(menu.getOrderNum())
                .path(menu.getPath())
                .pid(menu.getPid())
                .type(menu.getType())
                .code(menu.getCode())
                .build();
        return Responser.ok(menuVO);

    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleId del, BindingResult br) {
        menuService.delete(del.getId());
        return Responser.deleted();
    }

    @GetMapping("/tree")
    @ApiOperation("获取菜单树")
    public ResponseEntity<List<Tree<MenuPayload>>> tree() {
        return Responser.ok(menuService.findMenuTree());
    }

    @GetMapping("/user/tree")
    @ApiOperation("获取用户能访问的菜单树")
    public ResponseEntity<List<Tree<MenuPayload>>> userTrees() {

        return Responser.ok(menuService.findLeftMenuTreeBySelf());
    }
}
