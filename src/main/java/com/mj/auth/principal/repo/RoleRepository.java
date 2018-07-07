package com.mj.auth.principal.repo;

import com.mj.auth.principal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 */
public interface RoleRepository  extends JpaRepository<Role,Integer> {
}
