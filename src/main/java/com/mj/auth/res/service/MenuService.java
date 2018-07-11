package com.mj.auth.res.service;

import com.mj.auth.res.dto.MenuAddDTO;
import com.mj.auth.res.model.Menu;
import com.mj.core.data.tree.Tree;
import com.mj.core.service.BasicService;

import java.util.List;

/**
 * @author bvvy
 */
public interface MenuService extends BasicService<Menu,Integer> {

    List<Tree> findMenuTree();

    List<Tree> findLeftMenuTree();

    void add(MenuAddDTO menuAddDTO);

}
