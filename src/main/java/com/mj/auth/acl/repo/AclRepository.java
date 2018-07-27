package com.mj.auth.acl.repo;

import com.mj.auth.acl.model.Acl;
import com.mj.auth.acl.repo.custom.AclRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bvvy
 */
public interface AclRepository extends JpaRepository<Acl, Integer>, AclRepositoryCustom {
    /**
     * 删除角色的菜单权限
     *
     * @param pid pricipal id
     * @param resType resType
     * @param ptype pricipal Type
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteByPrincipalIdAndPrincipalTypeAndResType(Integer pid, String ptype, String resType);
}
