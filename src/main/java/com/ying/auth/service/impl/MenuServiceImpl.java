package com.ying.auth.service.impl;

import com.ying.auth.bo.MenuBO;
import com.ying.auth.dto.MenuAddDTO;
import com.ying.auth.model.Menu;
import com.ying.auth.payload.MenuPayload;
import com.ying.auth.repo.MenuRepository;
import com.ying.auth.repo.OperRepository;
import com.ying.auth.service.MenuService;
import com.ying.auth.val.MenuType;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.data.tree.ITreeMerger;
import com.ying.core.data.tree.Tree;
import com.ying.core.er.Loginer;
import com.ying.core.er.Treer;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
    public List<Tree<MenuPayload>> findMenuTree() {
        List<MenuBO> bos = menuRepository.findMenuTree();
        List<ITreeMerger<MenuPayload>> mergers = bos.stream().map(MenuBO::to).collect(toList());
        return Treer.genTree(mergers);
    }

    @Override
    public List<Tree<MenuPayload>> findLeftMenuTree() {
        List<MenuBO> bos = menuRepository.findLeftMenuTree();
        List<ITreeMerger<MenuPayload>> mergers = bos.stream().map(MenuBO::to).collect(toList());
        return Treer.genTree(mergers);
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
                .icon(menuAddDTO.getIcon())
                .build();
        this.add(menu);

    }


    @Override
    public List<Tree<MenuPayload>> findLeftMenuTreeBySelf() {
        // fixme
        List<MenuBO> bos = menuRepository.findLeftMenuTreeByUser(Loginer.userId());
        List<ITreeMerger<MenuPayload>> mergers = bos.stream().map(MenuBO::to).collect(toList());
        return Treer.genTree(mergers);
    }
}
