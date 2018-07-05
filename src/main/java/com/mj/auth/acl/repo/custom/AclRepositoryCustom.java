package com.mj.auth.acl.repo.custom;

import com.mj.auth.acl.model.Acl;

import java.util.List;

/**
 * @author bvvy
 */
public interface AclRepositoryCustom {

    /**
     *
     * @param roleId
     * @return
     */
    List<Acl> findByRole(Integer roleId);

    /**
     *
     * @param principalId
     * @param principalType
     * @return
     */
    List<Acl> findByPrincipal(Integer principalId,Integer principalType);
}
