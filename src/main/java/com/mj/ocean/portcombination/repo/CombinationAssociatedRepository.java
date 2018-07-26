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


    /**
     * 根据组合id删除数据
     * @param combinationId 组合id
     */
    void deleteByCombinationId(Integer combinationId);


    /**
     * 根据客户公司id，港口id，船公司id查询数据
     * @param companyId 客户公司id
     * @param portId 港口id
     * @param carrierId 船公司id
     * @return PortCombinationAssociated
     */
    PortCombinationAssociated findByCompanyIdAndPortIdAndCarrierId(Integer companyId,
                                                                   Integer portId,Integer carrierId);
}
