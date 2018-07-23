package com.mj.ocean.costcode.repo;

import com.mj.ocean.costcode.model.CostCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zejun
 * @date 2018/7/17 10:13
 */
@Repository
public interface CostCodeRepository extends JpaRepository<CostCode,Integer>,
        QuerydslPredicateExecutor<CostCode>{

    /**
     * 根据成本代码模糊查询
     * @param code
     * @return
     */
    List<CostCode> findByCodeLike(String code);

    /**
     * 检查成本代码是否重复
     * @param code 成本代码
     * @return CostCode
     */
    CostCode getByCode(String code);
}
