package com.ying.team.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.root.converter.Converter;
import com.ying.team.dto.MenuAddDTO;
import com.ying.team.model.Menu;
import com.ying.team.repo.MenuRepository;
import com.ying.team.service.MenuService;
import com.ying.team.vo.MenuTreeVO;
import com.ying.team.vo.MenuVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


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

        if (StringUtils.isBlank(menuAddDTO.getPid())) {
            menuAddDTO.setPid(Menu.DEFAULT_PID);
        }
        Menu menu = Menu.builder()
                .enabled(menuAddDTO.getEnabled())
                .name(menuAddDTO.getName())
                .orderNum(menuAddDTO.getOrderNum())
                .path(menuAddDTO.getPath())
                .pid(menuAddDTO.getPid())
                .icon(menuAddDTO.getIcon())
                .authority(menuAddDTO.getAuthority())
                .shortcut(menuAddDTO.getShortcut())
                .build();
        this.add(menu);

    }

    @Override
    public MenuVO getVO(String menuId) {
        Menu menu = this.get(menuId);
        return toVO(menu);
    }


    @Override
    public List<MenuVO> findByIds(Collection<String> ids) {
        return Converter.of(menuRepository.findByIdIn(ids)).convert(this::toVO);
    }

    @Override
    public Map<String, MenuVO> findMapByIds(Collection<String> ids) {
        return findByIds(ids).stream().collect(toMap(MenuVO::getId, vo -> vo));
    }

    @Override
    public MenuVO getByAuthority(String menuCode) {
        Menu menu =  menuRepository.getByAuthority(menuCode);
        return toVO(menu);
    }

    @Override
    public Set<String> findByMenuIdsByAuthorities(Set<String> authorities) {
        List<Menu> menus =  menuRepository.findByAuthorityIn(authorities);
        return menus.stream().map(Menu::getId).collect(Collectors.toSet());
    }


    @Override
    public Set<String> findShortcutByAuthorities(Set<String> authorities) {
        List<Menu> menus =  menuRepository.findShortcutByAuthorities(authorities);
        return menus.stream().map(Menu::getId).collect(Collectors.toSet());
    }



    private MenuVO toVO(Menu menu) {
        return MenuVO.builder()
                .enabled(menu.getEnabled())
                .id(menu.getId())
                .name(menu.getName())
                .orderNum(menu.getOrderNum())
                .path(menu.getPath())
                .pid(menu.getPid())
                .authority(menu.getAuthority())
                .icon(menu.getIcon())
                .shortcut(menu.getShortcut())
                .color(menu.getColor())
                .build();
    }

    @Override
    public List<MenuTreeVO> findTree() {
        List<Menu> pmenus = menuRepository.findByPidAndShortcut(Menu.DEFAULT_PID, false);
        return Converter.of(pmenus).convert(menu -> {
            List<Menu> cMenus = menuRepository.findByPidAndShortcut(menu.getId(), false);
            List<MenuTreeVO> children = cMenus.stream().map(
                    cMenu -> new MenuTreeVO(cMenu.getId(), cMenu.getIcon(), cMenu.getName(),cMenu.getColor())).collect(Collectors.toList());
            return new MenuTreeVO(menu.getId(), menu.getIcon(), menu.getName(), menu.getColor(), children);
        });
    }

    @Override
    public List<MenuTreeVO> findShortcutTree() {
        List<Menu> pmenus = menuRepository.findByPidAndShortcut(Menu.DEFAULT_PID, true);
        return Converter.of(pmenus).convert(menu -> {
            List<Menu> cMenus = menuRepository.findByPidAndShortcut(menu.getId(), true);
            List<MenuTreeVO> children = cMenus.stream().map(
                    cMenu -> new MenuTreeVO(cMenu.getId(), cMenu.getIcon(), cMenu.getName(),cMenu.getColor())).collect(Collectors.toList());
            return new MenuTreeVO(menu.getId(), menu.getIcon(), menu.getName(),menu.getColor(), children);
        });
    }
}
