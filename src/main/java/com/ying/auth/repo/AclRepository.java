package com.ying.auth.repo;

import com.ying.auth.model.Acl;
import com.ying.auth.repo.custom.AclRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bvvy
 */
public interface AclRepository extends JpaRepository<Acl, Integer>, AclRepositoryCustom {
    /**
     * 删除角色的菜单权限
     *
     * @param roleId role
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteByRoleId(Integer roleId);
}
