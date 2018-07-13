package com.mj.general.charge.repo;

import com.mj.general.charge.model.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zejun
 * @date 2018/7/10 09:22
 */
@Repository
public interface ChargeRepository extends JpaRepository<Charge,Integer>,QuerydslPredicateExecutor<Charge> {

    /**
     * 根据费用简称查询数据
     * @param chargeItemCode 费用简称
     * @return Charge
     */
    Charge getByChargeItemCode(String chargeItemCode);

    /**
     * 根据费用中文名查询数据
     * @param chargeItemCN 费用中文名
     * @return Charge
     */
    Charge getByChargeItemCN(String chargeItemCN);

    /**
     * 根据费用英文名查询数据
     * @param chargeItemEN 费用英文名
     * @return Charge
     */
    Charge getByChargeItemEN(String chargeItemEN);
}