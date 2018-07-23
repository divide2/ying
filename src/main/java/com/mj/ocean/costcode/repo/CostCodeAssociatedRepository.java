package com.mj.ocean.costcode.repo;

import com.mj.ocean.costcode.model.CostCodeAssociated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/17 10:21
 */
@Repository
public interface CostCodeAssociatedRepository extends JpaRepository<CostCodeAssociated,Integer>,
        QuerydslPredicateExecutor<CostCodeAssociated> {

    /**
     * 根据成本代码id查询数据集
     * @param costCodeId
     * @return
     */
    List<CostCodeAssociated> findByCostCodeId(Integer costCodeId);

    /**
     * 根据成本代码id删除数据
     * @param costCodeId 成本代码id
     */
    void deleteByCostCodeId(Integer costCodeId);
}
