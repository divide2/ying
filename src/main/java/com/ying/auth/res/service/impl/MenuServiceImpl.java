package com.ying.auth.res.service.impl;

import com.ying.auth.res.dto.MenuAddDTO;
import com.ying.auth.res.model.Menu;
import com.ying.auth.res.repo.MenuRepository;
import com.ying.auth.res.repo.OperRepository;
import com.ying.auth.res.service.MenuService;
import com.ying.auth.res.val.MenuType;
import com.ying.core.data.tree.Tree;
import com.ying.core.data.tree.TreeMerger;
import com.ying.core.er.Loginer;
import com.ying.core.er.Treer;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 */
@Service
public class MenuServiceImpl extends SimpleBasicServiceImpl<Menu,Integer,MenuRepository> implements MenuService {

    private final MenuRepository menuRepository;
    private final OperRepository operRepository;

    public MenuServiceImpl(MenuRepository menuRepository,
                           OperRepository operRepository) {
        this.menuRepository = menuRepository;
        this.operRepository = operRepository;
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

    @Override
    public void add(MenuAddDTO menuAddDTO) {

        Menu menu = Menu.builder()
                .enabled(menuAddDTO.getEnabled())
                .name(menuAddDTO.getName())
                .orderNum(menuAddDTO.getOrderNum())
                .path(menuAddDTO.getPath())
                .pid(menuAddDTO.getPid())
                .type(MenuType.NAV)
                .code(menuAddDTO.getCode())
                .build();
        this.add(menu);

    }


    @Override
    public List<Tree> findLeftMenuTreeBySelf() {
        List<TreeMerger> tree = menuRepository.findLeftMenuTreeByUser(Loginer.userId());

        return Treer.genTree(tree);
    }
}
