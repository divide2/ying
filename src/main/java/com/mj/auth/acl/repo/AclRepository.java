package com.mj.auth.acl.repo;

import com.mj.auth.acl.model.Acl;
import com.mj.auth.acl.repo.custom.AclRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 */
public interface AclRepository extends JpaRepository<Acl, Integer>,AclRepositoryCustom {

    Acl getByPrincipalIdAndPrincipalTypeAndResIdAndResType(Integer pid, String ptype, Integer resId, String resType);
}
