package com.ying.auth.repo.cutom;

import com.ying.auth.model.User;
import com.ying.auth.vo.TeamVO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * @author bvvy
 * <p>
 * 用户repo
 */
public interface UserRepositoryCustom {

    /**
     * 通过用户名获取用户角色
     *
     * @param username username
     * @return grant
     */
    List<SimpleGrantedAuthority> findUserRolesByUsername(String username);

    /**
     * 获取某个角色下的所有用户
     *
     * @param roleId 角色
     * @return 用户
     */
    List<User> findUsersByRole(Integer roleId);

    /**
     * 通过登录账号获取用户
     * @param username 账号
     * @return user
     */
    User getByAccount(String username);

    /**
     * 获取用户的团队
     * @param userId 用户
     * @return 团队
     */
    List<TeamVO> listUserTeam(Integer userId);


}
