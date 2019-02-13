package com.ying.auth.service;

import com.ying.auth.dto.MenuAddDTO;
import com.ying.auth.model.Menu;
import com.ying.auth.payload.MenuPayload;
import com.ying.core.basic.service.BasicService;
import com.ying.core.data.tree.Tree;

import java.util.List;

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

}
