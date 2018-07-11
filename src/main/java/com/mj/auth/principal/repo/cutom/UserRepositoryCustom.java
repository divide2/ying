package com.mj.auth.principal.repo.cutom;

import com.mj.auth.principal.model.User;
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
}
