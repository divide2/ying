package com.mj.auth.res.service.impl;

import com.mj.auth.res.dto.MenuAddDTO;
import com.mj.auth.res.model.Menu;
import com.mj.auth.res.repo.MenuRepository;
import com.mj.auth.res.repo.OperRepository;
import com.mj.auth.res.service.MenuService;
import com.mj.core.data.tree.Tree;
import com.mj.core.data.tree.TreeMerger;
import com.mj.core.er.Treer;
import com.mj.core.service.impl.SimpleBasicServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .type(menuAddDTO.getType())
                .build();
        this.add(menu);

    }
}
