package com.mj.general.carrier.repo;

import com.mj.general.carrier.model.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zejun
 * @date 2018/7/9 14:14
 */
@Repository
public interface CarrierRepository extends JpaRepository<Carrier,Integer>, QuerydslPredicateExecutor<Carrier> {

    /**
     * 根据船公司简称查数据
     * @param carrierCode 船公司简称
     * @return Carrier
     */
    Carrier getByCarrierCode(String carrierCode);

    /**
     * 根据船公司英文名查数据
     * @param carrierEN 船公司英文名
     * @return Carrier
     */
    Carrier getByCarrierEN(String carrierEN);

    /**
     * 根据船公司中文名查数据
     * @param carrierCN 船公司中文名
     * @return Carrier
     */
    Carrier getByCarrierCN(String carrierCN);
}