package com.ying.auth.service.impl;

import com.ying.auth.dto.MenuAddDTO;
import com.ying.auth.model.Menu;
import com.ying.auth.repo.MenuRepository;
import com.ying.auth.service.MenuService;
import com.ying.auth.val.MenuType;
import com.ying.auth.vo.MenuVO;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 */
@Service
public class MenuServiceImpl extends SimpleBasicServiceImpl<Menu, Integer, MenuRepository> implements MenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository
    ) {
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
                .type(MenuType.NAV)
                .code(menuAddDTO.getCode())
                .icon(menuAddDTO.getIcon())
                .build();
        this.add(menu);

    }

    @Override
    public MenuVO getVO(Integer menuId) {
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
                .type(menu.getType())
                .code(menu.getCode())
                .build();
    }

}
