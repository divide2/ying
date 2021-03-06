package com.divide2.basis.repo;

import com.divide2.basis.model.GeneralCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zejun
 * @date 2018/7/27 18:22
 */
@Repository
public interface CountryRepository extends JpaRepository<GeneralCountry,Integer>,QuerydslPredicateExecutor<GeneralCountry> {
}
