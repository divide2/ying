package com.mj.ocean.surcharge.repo;

import com.mj.ocean.surcharge.model.Surcharge;
import com.mj.ocean.surcharge.repo.custom.SurchargeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/7/18
 */
public interface SurchargeRepository extends SurchargeRepositoryCustom, JpaRepository<Surcharge, Integer>,QuerydslPredicateExecutor<Surcharge> {

    /**
     * 通过公司 and 启运港/组合 目的港/组合 id删除
     * @param carrierId 公司
     * @param podId 启运港/组合
     * @param pomId 目的港/组合
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteByCarrierIdAndPodIdAndPomIdAndCostType(Integer carrierId, Integer podId, Integer pomId, String costType);

    /**
     * 获取具有相同船公司 启运港，目的港的附加费信息
     * @param carrierId 船公司
     * @param pomId 启运港
     * @param podId 目的港
     * @return 附加费
     */
    List<Surcharge> findByCarrierIdAndPomIdAndPodIdAndCostType(Integer carrierId, Integer pomId, Integer podId,String costType);
}
