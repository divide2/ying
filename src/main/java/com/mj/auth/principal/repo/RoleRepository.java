package com.mj.auth.principal.repo;

import com.mj.auth.principal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author bvvy
 */
public interface RoleRepository extends JpaRepository<Role, Integer>, QuerydslPredicateExecutor<Role> {
}
