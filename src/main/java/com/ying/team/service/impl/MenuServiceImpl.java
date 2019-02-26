package com.ying.team.service.impl;

import com.ying.team.dto.MenuAddDTO;
import com.ying.team.model.Menu;
import com.ying.team.repo.MenuRepository;
import com.ying.team.vo.MenuVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.team.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class MenuServiceImpl extends SimpleBasicServiceImpl<Menu, String, MenuRepository> implements MenuService {
    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void add(MenuAddDTO menuAddDTO) {

        Menu menu = Menu.builder()
                .enabled(menuAddDTO.getEnabled())
                .name(menuAddDTO.getName())
                .orderNum(menuAddDTO.getOrderNum())
                .path(menuAddDTO.getPath())
                .pid(menuAddDTO.getPid())
                .icon(menuAddDTO.getIcon())
                .build();
        this.add(menu);

    }

    @Override
    public MenuVO getVO(String menuId) {
        Menu menu = this.get(menuId);
        return toVO(menu);
    }

    @Override
    public MenuVO getByCode(String menuCode) {
        Menu menu =  menuRepository.getByCode(menuCode);
        return toVO(menu);
    }

    private MenuVO toVO(Menu menu) {
        return MenuVO.builder()
                .enabled(menu.getEnabled())
                .id(menu.getId())
                .name(menu.getName())
                .orderNum(menu.getOrderNum())
                .path(menu.getPath())
                .pid(menu.getPid())
                .code(menu.getCode())
                .icon(menu.getIcon())
                .build();
    }

}
