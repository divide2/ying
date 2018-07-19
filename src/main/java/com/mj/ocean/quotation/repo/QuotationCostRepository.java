package com.mj.ocean.quotation.repo;

import com.mj.ocean.quotation.model.QuotationCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/17 18:23
 */
public interface QuotationCostRepository extends JpaRepository<QuotationCost,Integer>,
        QuerydslPredicateExecutor<QuotationCost> {

    List<QuotationCost> findByQuotationId(int quotationId);
}
