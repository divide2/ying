package com.ying.auth.repo.custom;

import java.util.List;

/**
 * @author bvvy
 */
public interface AclRepositoryCustom {

    /**
     * 获取一个角色能访问的code
     *
     * @return code
     */
    List<String> findAuthorities(Integer roleId);
}
