package com.mj.auth.acl.repo;

import com.mj.auth.acl.model.Acl;
import com.mj.auth.acl.repo.custom.AclRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AclRepository extends JpaRepository<Acl, Integer>,AclRepositoryCustom {

}
