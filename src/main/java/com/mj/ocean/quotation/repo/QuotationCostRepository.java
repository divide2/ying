package com.mj.ocean.quotation.repo;

import com.mj.ocean.portcombination.model.PortCombinationAssociated;
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

    /**
     * 根据报价id查询数据
     * @param quotationId 报价id
     * @return List<QuotationCost>
     */
    List<QuotationCost> findByQuotationId(int quotationId);

    /**
     * 根据报价id删除数据
     * @param quotationId 报价id
     */
    void deleteByQuotationId(int quotationId);

}
