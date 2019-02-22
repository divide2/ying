package com.ying.auth.service.impl;

import com.ying.auth.repo.UserTeamRoleRepository;
import com.ying.auth.service.UserTeamRoleService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2019/2/13
 */
@Service
public class UserTeamRoleServiceImpl implements UserTeamRoleService {

    private final UserTeamRoleRepository userTeamRoleRepository;

    public UserTeamRoleServiceImpl(UserTeamRoleRepository userTeamRoleRepository) {
        this.userTeamRoleRepository = userTeamRoleRepository;
    }


}
