package com.ying.team.service;

import com.ying.team.vo.WorkbenchVO;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/26
 */
public interface WorkbenchService {

    List<WorkbenchVO> getTeamWorkbench(String teamId);
}
