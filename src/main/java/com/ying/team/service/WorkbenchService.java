package com.ying.team.service;

import com.ying.team.dto.GroupMenuDTO;
import com.ying.team.dto.MenuGroupDTO;
import com.ying.team.vo.WorkbenchVO;

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

}
