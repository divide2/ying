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

    Set<String> findByMenuIdsByAuthorities(Set<String> authorities);

    List<MenuTreeVO> findTree();
}
