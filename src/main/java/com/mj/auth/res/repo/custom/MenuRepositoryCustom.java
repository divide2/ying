package com.mj.auth.res.repo.custom;

import com.mj.core.data.tree.TreeMerger;

import java.util.List;

/**
 * @author bvvy
 */
public interface MenuRepositoryCustom {

    /**
     * 获取菜单树
     * @return
     */
    List<TreeMerger> findMenuTree();

}
