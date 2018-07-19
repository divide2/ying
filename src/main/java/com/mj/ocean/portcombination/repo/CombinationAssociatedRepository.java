package com.mj.ocean.portcombination.repo;

import com.mj.ocean.portcombination.model.PortCombinationAssociated;
import com.mj.ocean.portcombination.repo.custom.CombinationAssociatedRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author zejun
 * @date 2018/7/16 21:19
 */
public interface CombinationAssociatedRepository extends CombinationAssociatedRepositoryCustom,
        JpaRepository<PortCombinationAssociated,Integer>,
        QuerydslPredicateExecutor<PortCombinationAssociated> {

    void deleteByCombinationId(Integer combinationId);


}
