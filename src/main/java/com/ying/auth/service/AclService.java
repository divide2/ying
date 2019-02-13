package com.ying.auth.service;

import com.ying.auth.dto.RolePerAddDTO;

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
     * 获取这个人在团队下可以访问的功能
     * @param userId userid
     * @param groupId groupid
     * @return 功能码
     */
    List<String> listAuthorities(Integer userId, String groupId);
}
