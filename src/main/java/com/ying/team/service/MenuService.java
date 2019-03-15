package com.ying.team.service;

import com.ying.core.basic.service.BasicService;
import com.ying.team.dto.MenuAddDTO;
import com.ying.team.model.Menu;
import com.ying.team.vo.MenuTreeVO;
import com.ying.team.vo.MenuVO;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author bvvy
 */
public interface MenuService extends BasicService<Menu, String> {

    /**
     * 通过menuAddDTO新增
     *
     * @param menuAddDTO dto
     */
    void add(MenuAddDTO menuAddDTO);

    MenuVO getVO(String menuId);

    List<MenuVO> findByIds(Collection<String> ids);


    Map<String, MenuVO> findMapByIds(Collection<String> ids);

    MenuVO getByAuthority(String menuCode);

    /**
     * workbench中使用
     *
     * @param authorities authority
     * @return menuIds
     */
    Set<String> findMenuIdsByAuthorities(Set<String> authorities);


    /**
     * 获取用户能访问的子菜单 在回显已授权菜单时使用
     *
     * @param authorities 权限
     * @return menuIds
     */
    Set<String> findChildrenMenuIdsByAuthorities(Set<String> authorities);

    Set<String> findShortcutByAuthorities(Set<String> authorities);

    /**
     * 全部节点的树 再授权时使用
     *
     * @return menutree
     */
    List<MenuTreeVO> findTree();

    /**
     * 只有快捷方式的树 再添加workbench时使用
     *
     * @return menuTree
     */
    List<MenuTreeVO> findShortcutTree();

}
