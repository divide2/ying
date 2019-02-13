package com.ying.basis.repo;

import com.ying.auth.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/16
 */
public interface CompanyRepository  extends JpaRepository<Group,Integer> {
}
