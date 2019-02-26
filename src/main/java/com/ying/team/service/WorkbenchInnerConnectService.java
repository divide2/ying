package com.ying.team.service;

import com.ying.core.basic.service.ConnectService;

import java.util.Set;

/**
 * @author bvvy
 * @date 2019/2/26
 */
public interface WorkbenchInnerConnectService extends ConnectService {


    Set<String> listTeamUserMenuIds(String teamId, Integer userId);
}
