package com.ying.team.service.impl;

import com.ying.team.model.Workbench;
import com.ying.team.repo.MenuGroupRepository;
import com.ying.team.repo.WorkbenchRepository;
import com.ying.team.service.WorkbenchService;
import com.ying.team.vo.WorkbenchVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/26
 */
@Service
public class WorkbenchServiceImpl implements WorkbenchService {
    private final WorkbenchRepository workbenchRepository;
    private final MenuGroupRepository menuGroupRepository;

    public WorkbenchServiceImpl(WorkbenchRepository workbenchRepository,
                                MenuGroupRepository menuGroupRepository) {
        this.workbenchRepository = workbenchRepository;
        this.menuGroupRepository = menuGroupRepository;
    }

    @Override
    public List<WorkbenchVO> getTeamWorkbench(String teamId) {
        List<Workbench> workbenches = workbenchRepository.findByTeamId(teamId);
        // 查找分组
        menuGroupRepository.findByTeamId(teamId);
        // 合并数据
        return null;
    }
}
