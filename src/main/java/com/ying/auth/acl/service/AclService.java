package com.ying.auth.acl.service;

import com.ying.auth.principal.dto.RolePerAddDTO;

import java.util.List;

/**
 * @author bvvy
 */
public interface AclService {


    /**
     * 添加角色权限
     *
     * @param rolePerAddDTO dto
     */
    void addRolePerm(RolePerAddDTO rolePerAddDTO);

    /**
     * 获取角色能访问的菜单ids
     *
     * @param roleId 角色
     * @return 菜单ids
     */
    List<Integer> findMenuIdsByRole(Integer roleId);
}
