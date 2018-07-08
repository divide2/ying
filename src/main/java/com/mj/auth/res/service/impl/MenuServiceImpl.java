package com.mj.auth.res.service.impl;

import com.mj.auth.res.model.Menu;
import com.mj.auth.res.repo.MenuRepository;
import com.mj.auth.res.service.MenuService;
import com.mj.core.data.tree.Tree;
import com.mj.core.data.tree.TreeMerger;
import com.mj.core.er.Treer;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 */
@Service
public class MenuServiceImpl extends SimpleBasicServiceImpl<Menu,Integer,MenuRepository> implements MenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
    @Override
    public List<Tree> findMenuTree() {
        List<TreeMerger> treeMerger = menuRepository.findMenuTree();
        return Treer.genTree(treeMerger);
    }

    @Override
    public List<Tree> findLeftMenuTree() {
        return Treer.genTree(menuRepository.findLeftMenuTree());
    }

}
