package com.ying.team.service;

import com.ying.core.basic.service.ConnectService;
import com.ying.team.vo.MenuVO;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author bvvy
 * @date 2019/2/26
 */
public interface WorkbenchInnerConnectService extends ConnectService {


    Set<String> listTeamUserMenuIds(String teamId, Integer userId);

    Map<String, MenuVO> findMapByMenuIds(Collection<String> menuIds);
}
