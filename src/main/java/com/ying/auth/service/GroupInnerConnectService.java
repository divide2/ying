package com.ying.auth.service;

import com.ying.auth.vo.RoleVO;
import com.ying.auth.vo.UserVO;
import com.ying.core.basic.service.ConnectService;

/**
 * @author bvvy
 * @date 2019/2/17
 */
public interface GroupInnerConnectService extends ConnectService {
    UserVO getUser(Integer userId);

    RoleVO getRole(Integer roleId);
}
