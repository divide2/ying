package com.ying.auth.service.impl;

import com.ying.auth.repo.UserGroupRoleRepository;
import com.ying.auth.service.UserGroupRoleService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2019/2/13
 */
@Service
public class UserGroupRoleServiceImpl implements UserGroupRoleService {

    private final UserGroupRoleRepository userGroupRoleRepository;

    public UserGroupRoleServiceImpl(UserGroupRoleRepository userGroupRoleRepository) {
        this.userGroupRoleRepository = userGroupRoleRepository;
    }


}
