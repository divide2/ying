package com.mj.auth.user.repo.cutom;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface UserRepositoryCustom {

    List<SimpleGrantedAuthority> findUserRolesByUsername(String username);
}
