package com.mj.auth.res.repo.custom;

import com.mj.core.data.tree.TreeMerger;

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
    List<TreeMerger> findLeftMenuTreeByUser(Integer userId);

    /**
     * 获取整个菜单树
     * @return tree
     */
    List<TreeMerger> findMenuTree();

    /**
     * 获取左边导航
     * @return tree
     */
    List<TreeMerger> findLeftMenuTree();
}
