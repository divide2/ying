package com.mj.auth.acl.repo.custom;

import com.mj.auth.acl.model.Acl;

import java.util.List;

public interface AclRepositoryCustom {

    List<Acl> findByRole(Integer roleId);

    List<Acl> findByPrincipal(Integer principalId,Integer principalType);
}
