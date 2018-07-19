package com.mj.ocean.quotation.repo;

import com.mj.ocean.quotation.model.Quotation;
import com.mj.ocean.quotation.repo.custom.QuotationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author zejun
 * @date 2018/7/17 18:21
 */
public interface QuotationRepository extends QuotationRepositoryCustom,
        JpaRepository<Quotation,Integer>,QuerydslPredicateExecutor<Integer> {
}
