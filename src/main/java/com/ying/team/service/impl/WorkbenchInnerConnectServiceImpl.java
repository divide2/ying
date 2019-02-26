package com.ying.team.service.impl;

import com.ying.team.service.AclService;
import com.ying.team.service.WorkbenchInnerConnectService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author bvvy
 * @date 2019/2/26
 */
@Service
public class WorkbenchInnerConnectServiceImpl implements WorkbenchInnerConnectService {
    private final AclService aclService;

    public WorkbenchInnerConnectServiceImpl(AclService aclService) {
        this.aclService = aclService;
    }

    @Override
    public Set<String> listTeamUserMenuIds(String teamId, Integer userId) {
        return aclService.listTeamUserMenuIds(teamId, userId);
    }
}
