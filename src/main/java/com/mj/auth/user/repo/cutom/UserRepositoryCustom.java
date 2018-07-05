package com.mj.auth.user.repo.cutom;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * @author bvvy
 *
 * 用户repo
 */
public interface UserRepositoryCustom {

    /**
     *
     * @param username
     * @return
     */
    List<SimpleGrantedAuthority> findUserRolesByUsername(String username);
}
