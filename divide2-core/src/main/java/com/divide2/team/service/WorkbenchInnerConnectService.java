package com.divide2.team.service;

import com.divide2.core.basic.service.ConnectService;
import com.divide2.team.vo.MenuTreeVO;
import com.divide2.team.vo.MenuVO;

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

    List<MenuTreeVO> findShortcutMenus();


}
