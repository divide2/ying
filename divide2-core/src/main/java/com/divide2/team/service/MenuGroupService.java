package com.divide2.team.service;

import com.divide2.core.basic.service.BasicService;
import com.divide2.team.dto.MenuGroupDTO;
import com.divide2.team.dto.MenuGroupDeleteDTO;
import com.divide2.team.dto.MenuGroupUpdateDTO;
import com.divide2.team.model.MenuGroup;

/**
 * @author bvvy
 * @date 2019/2/27
 */
public interface MenuGroupService extends BasicService<MenuGroup,String> {
    void delete(MenuGroupDeleteDTO dto);

    void add(MenuGroupDTO dto);

    void update(MenuGroupUpdateDTO dto);
}
