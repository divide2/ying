package com.mj.ocean.quotation.repo;

import com.mj.ocean.quotation.model.Quotation;
import com.mj.ocean.quotation.repo.custom.QuotationRepositoryCustom;
import com.mj.ocean.quotation.vo.QuotationVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/17 18:21
 */
public interface QuotationRepository extends QuotationRepositoryCustom,
        JpaRepository<Quotation,Integer>,QuerydslPredicateExecutor<Integer> {

    @Query(value = "select * from ocean_fc_quotation where company_id = ?1 and cost_service_code = 'general' ORDER BY created_date DESC ",nativeQuery = true)
    List<Quotation> findOrderByCreatedDateDesc(Integer companyId);
}
