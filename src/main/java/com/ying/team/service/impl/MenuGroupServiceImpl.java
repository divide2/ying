package com.ying.team.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.team.dto.MenuGroupDTO;
import com.ying.team.dto.MenuGroupDeleteDTO;
import com.ying.team.dto.MenuGroupUpdateDTO;
import com.ying.team.model.MenuGroup;
import com.ying.team.repo.MenuGroupRepository;
import com.ying.team.repo.WorkbenchRepository;
import com.ying.team.service.MenuGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bvvy
 * @date 2019/2/27
 */
@Service
public class MenuGroupServiceImpl extends SimpleBasicServiceImpl<MenuGroup, String, MenuGroupRepository>
        implements MenuGroupService {

    private final WorkbenchRepository workbenchRepository;

    public MenuGroupServiceImpl(WorkbenchRepository workbenchRepository) {
        this.workbenchRepository = workbenchRepository;
    }

    @Override
    @Transactional
    public void delete(MenuGroupDeleteDTO dto) {
        // 删除menuGroup和下面的菜单
        workbenchRepository.deleteByTeamIdAndMenuGroupId(dto.getTeamId(), dto.getMenuGroupId());
        this.delete(dto.getMenuGroupId());
    }
    @Override
    public void add(MenuGroupDTO dto) {
        MenuGroup menuGroup = new MenuGroup();
        menuGroup.setName(dto.getName());
        menuGroup.setTeamId(dto.getTeamId());
        menuGroup.setOrderNum(1); // todo add max num
        this.add(menuGroup);
    }

    @Override
    public void update(MenuGroupUpdateDTO dto) {
        MenuGroup group = this.get(dto.getId());
        group.setName(dto.getName());
        this.update(group);
    }
}
