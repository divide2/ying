package com.mj.ocean.portcombination.repo;

import com.mj.ocean.portcombination.model.PortCombination;
import com.mj.ocean.portcombination.repo.custom.PortCombinationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author zejun
 * @date 2018/7/13 16:10
 */
@Repository
public interface PortCombinationRepository extends PortCombinationRepositoryCustom,
        JpaRepository<PortCombination,Integer>,QuerydslPredicateExecutor<PortCombination> {
}
