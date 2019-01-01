package com.ying.basis.repo;

import com.ying.basis.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/16
 */
public interface CompanyRepository  extends JpaRepository<Company,Integer> {
}
