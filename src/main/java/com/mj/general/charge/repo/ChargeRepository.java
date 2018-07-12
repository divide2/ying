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

    Charge getByChargeItemCode(String chargeItemCode);

    Charge getByChargeItemCN(String chargeItemCN);

    Charge getByChargeItemEN(String chargeItemEN);
}
