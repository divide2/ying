package com.divide2.team.controller;

import com.divide2.core.data.del.SingleStringId;
import com.divide2.core.data.resp.Messager;
import com.divide2.core.er.Responser;
import com.divide2.team.dto.MenuAddDTO;
import com.divide2.team.dto.MenuUpdateDTO;
import com.divide2.team.model.Menu;
import com.divide2.team.service.MenuService;
import com.divide2.team.vo.MenuTreeVO;
import com.divide2.team.vo.MenuVO;
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

    public MenuController(MenuService menuService
    ) {
        this.menuService = menuService;

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
        menuService.update(menu);
        return Responser.updated();
    }

    @GetMapping("/{id}")
    @ApiOperation("获取单个")
    public ResponseEntity<MenuVO> get(@PathVariable String id) {
        MenuVO menu = menuService.getVO(id);
        return Responser.ok(menu);

    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResponseEntity<Messager> delete(@Valid @RequestBody SingleStringId del, BindingResult br) {
        menuService.delete(del.getId());
        return Responser.deleted();
    }

    @GetMapping("/tree")
    @ApiOperation("获取菜单树")
    public ResponseEntity<List<MenuTreeVO>> findTree() {
        return Responser.ok(menuService.findTree());
    }

}
