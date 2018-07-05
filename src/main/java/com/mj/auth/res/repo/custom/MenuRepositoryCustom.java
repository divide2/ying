package com.mj.auth.res.repo.custom;

import com.mj.core.data.tree.TreeMerger;

import java.util.List;

/**
 * @author bvvy
 */
public interface MenuRepositoryCustom {

    /**
     *
     * @return
     */
    List<TreeMerger<Integer, String>> findPerMenu();
}
