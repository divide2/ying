package com.mj.ocean.portcombination.repo;

import com.mj.ocean.portcombination.model.PortCombinationAssociated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zejun
 * @date 2018/7/16 21:19
 */
@Repository
public interface CombinationAssociatedRepository extends JpaRepository<PortCombinationAssociated,Integer>,
        QuerydslPredicateExecutor<PortCombinationAssociated> {

    void deleteByCombinationId(Integer combinationId);
}
