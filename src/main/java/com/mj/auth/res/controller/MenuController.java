package com.mj.auth.res.controller;

import com.mj.auth.res.dto.MenuAddDTO;
import com.mj.auth.res.dto.MenuUpdateDTO;
import com.mj.auth.res.model.Menu;
import com.mj.auth.res.service.MenuService;
import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author bvvy
 * 菜单contorller
 */
@RestController
@RequestMapping("/v1/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<Messager> add(@RequestBody MenuAddDTO menuAddDTO, BindingResult br) {
        Menu menu = Menu.builder()
                .enabled(menuAddDTO.getEnabled())
                .name(menuAddDTO.getName())
                .orderNum(menuAddDTO.getOrderNum())
                .path(menuAddDTO.getPath())
                .pid(menuAddDTO.getPid())
                .build();
        menuService.add(menu);
        return Responser.created();
    }

    @PatchMapping
    public ResponseEntity<Messager> update(@RequestBody MenuUpdateDTO menuUpdateDTO, BindingResult br) {
        Menu menu = menuService.get(menuUpdateDTO.getId());
        menu.setEnabled(menuUpdateDTO.getEnabled());
        menu.setName(menuUpdateDTO.getName());
        menu.setPath(menuUpdateDTO.getPath());
        menu.setOrderNum(menuUpdateDTO.getOrderNum());
        menu.setPid(menuUpdateDTO.getPid());
        menuService.update(menu);
        return Responser.updated();
    }


}
