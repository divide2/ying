package com.ying.auth.service.impl;

import com.ying.auth.service.GroupInnerConnectService;
import com.ying.auth.service.RoleService;
import com.ying.auth.service.UserService;
import com.ying.auth.vo.RoleVO;
import com.ying.auth.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2019/2/17
 */
@Service
public class GroupInnerConnectServiceImpl implements GroupInnerConnectService {

    private final UserService userService;
    private final RoleService roleService;

    public GroupInnerConnectServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserVO getUser(Integer userId) {
        return userService.getVO(userId);
    }

    @Override
    public RoleVO getRole(Integer roleId) {
        return roleService.getVO(roleId);
    }

}
