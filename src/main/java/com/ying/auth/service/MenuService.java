package com.ying.auth.service;

import com.ying.auth.dto.MenuAddDTO;
import com.ying.auth.model.Menu;
import com.ying.auth.vo.MenuVO;
import com.ying.core.basic.service.BasicService;

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
