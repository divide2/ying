package com.mj.auth.res.service;

import com.mj.auth.res.dto.MenuAddDTO;
import com.mj.auth.res.model.Menu;
import com.mj.core.data.tree.Tree;
import com.mj.core.service.BasicService;

import java.util.List;

/**
 * @author bvvy
 */
public interface MenuService extends BasicService<Menu, Integer> {

    /**
     * 获取整个菜单树
     *
     * @return 菜单树
     */
    List<Tree> findMenuTree();

    /**
     * 获取整个左边菜单树
     *
     * @return tree
     */
    List<Tree> findLeftMenuTree();

    /**
     * 通过menuAddDTO新增
     *
     * @param menuAddDTO dto
     */
    void add(MenuAddDTO menuAddDTO);

    /**
     * 获取用户能访问的左边的菜单
     *
     * @return tree
     */
    List<Tree> findLeftMenuTreeBySelf();
}
