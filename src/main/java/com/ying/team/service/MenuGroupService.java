package com.ying.team.service;

import com.ying.core.basic.service.BasicService;
import com.ying.team.dto.MenuGroupDTO;
import com.ying.team.dto.MenuGroupDeleteDTO;
import com.ying.team.dto.MenuGroupUpdateDTO;
import com.ying.team.model.MenuGroup;

/**
 * @author bvvy
 * @date 2019/2/27
 */
public interface MenuGroupService extends BasicService<MenuGroup,String> {
    void delete(MenuGroupDeleteDTO dto);

    void add(MenuGroupDTO dto);

    void update(MenuGroupUpdateDTO dto);
}
