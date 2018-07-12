package com.mj.auth.acl.service;

import com.mj.auth.principal.dto.RolePerAddDTO;

import java.util.List;

/**
 * @author bvvy
 */
public interface AclService {


    /**
     *
     * @param rolePerAddDTO
     */
    void addRolePerm(RolePerAddDTO rolePerAddDTO);

    /**
     * 获取角色能访问的菜单ids
     * @param roleId 角色
     * @return 菜单ids
     */
    List<Integer> findMenuIdsByRole(Integer roleId);
}
