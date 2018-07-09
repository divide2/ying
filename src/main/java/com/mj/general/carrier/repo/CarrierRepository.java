package com.mj.general.carrier.repo;

import com.mj.general.carrier.model.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @auther: zejun
 * @date: 2018/7/9 14:14
 */
@Repository
public interface CarrierRepository extends JpaRepository<Carrier,Integer>, QuerydslPredicateExecutor<Carrier> {

}
