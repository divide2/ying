package com.divide2.team.service;

import com.divide2.team.dto.GroupMenuDTO;
import com.divide2.team.vo.WorkbenchMenuVO;
import com.divide2.team.vo.WorkbenchVO;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/26
 */
public interface WorkbenchService {

    List<WorkbenchVO> getTeamWorkbench(String teamId);

    void deleteGroupMenu(GroupMenuDTO dto);

    List<WorkbenchVO> getTeamUserWorkbench(String teamId, Integer userId);

    void addGroupMenu(GroupMenuDTO dto);

    List<WorkbenchMenuVO> listWorkbenchMenu(String menuGroupId);
}
