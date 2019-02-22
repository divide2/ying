package com.ying.team.service;

import com.ying.team.dto.RolePerAddDTO;

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
     * @param teamId teamId
     * @return 功能码
     */
    List<String> listAuthorities(Integer userId, String teamId);
}
