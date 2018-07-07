package com.mj.auth.acl.service;

import com.mj.auth.principal.dto.RolePerAddDTO;

/**
 * @author bvvy
 */
public interface AclService {


    void addRolePerm(RolePerAddDTO rolePerAddDTO);
}
