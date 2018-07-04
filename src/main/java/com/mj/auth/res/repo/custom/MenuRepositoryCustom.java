package com.mj.auth.res.repo.custom;

import com.mj.core.data.tree.TreeMerger;

import java.util.List;

public interface MenuRepositoryCustom {

    List<TreeMerger<Integer, String>> findPerMenu();
}
