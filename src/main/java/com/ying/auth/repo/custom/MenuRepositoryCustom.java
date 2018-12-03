package com.ying.auth.repo.custom;

import com.ying.auth.bo.MenuBO;

import java.util.List;

/**
 * @author bvvy
 */
public interface MenuRepositoryCustom {


    /**
     * 获取一个用户的左边导航菜单
     * @param userId 用户id
     * @return 树
     */
    List<MenuBO> findLeftMenuTreeByUser(Integer userId);

    /**
     * 获取整个菜单树
     * @return tree
     */
    List<MenuBO> findMenuTree();

    /**
     * 获取左边导航
     * @return tree
     */
    List<MenuBO> findLeftMenuTree();
}
