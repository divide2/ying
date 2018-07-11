package com.mj.auth.principal.repo;

import com.mj.auth.principal.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bvvy
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    /**
     * 通过角色id来删除
     *
     * @param roleId roleId
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteByRoleId(Integer roleId);
}
