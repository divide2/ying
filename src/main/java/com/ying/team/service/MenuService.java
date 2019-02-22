package com.ying.team.service;

import com.ying.core.basic.service.BasicService;
import com.ying.team.dto.MenuAddDTO;
import com.ying.team.model.Menu;
import com.ying.team.vo.MenuVO;

/**
 * @author bvvy
 */
public interface MenuService extends BasicService<Menu, Integer> {

    /**
     * 通过menuAddDTO新增
     *
     * @param menuAddDTO dto
     */
    void add(MenuAddDTO menuAddDTO);

    MenuVO getVO(Integer menuId);

    MenuVO getByCode(String menuCode);
}
