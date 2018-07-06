package com.mj.auth.user.repo;

import com.mj.auth.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 */
public interface RoleRepository  extends JpaRepository<Role,Integer> {
}
