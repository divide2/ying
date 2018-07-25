package com.mj.ocean.portcombination.repo.impl;

import com.mj.general.carrier.model.QCarrier;
import com.mj.general.port.model.QPort;
import com.mj.ocean.portcombination.model.QPortCombination;
import com.mj.ocean.portcombination.model.QPortCombinationAssociated;
import com.mj.ocean.portcombination.repo.custom.CombinationAssociatedRepositoryCustom;
import com.mj.ocean.portcombination.vo.CombinationAssociatedVO;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.sql.SQLExpressions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;

/**
 * @author zejun
 * @date 2018/7/18 09:40
 */
@Repository
public class CombinationAssociatedRepositoryImpl implements CombinationAssociatedRepositoryCustom {

    private final EntityManager entityManager;

    public CombinationAssociatedRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CombinationAssociatedVO> findByCombinationId(Integer combinationId) {
        QPort port = QPort.port;
        QCarrier carrier = QCarrier.carrier;
        QPortCombinationAssociated portCombinationAssociated = QPortCombinationAssociated.portCombinationAssociated;

        JPAQuery<CombinationAssociatedVO> query = new JPAQuery<>(entityManager);
        query = query.select(constructor(CombinationAssociatedVO.class,
                portCombinationAssociated.id,
                portCombinationAssociated.combinationId,
                carrier.id,carrier.carrierCode,port.id,
                port.portCode,port.portEN,
                port.countryEN)).from(portCombinationAssociated)
                .leftJoin(carrier)
                .on(carrier.id.eq(portCombinationAssociated.carrierId))
                .leftJoin(port)
                .on(port.id.eq(portCombinationAssociated.portId))
                .where(portCombinationAssociated.combinationId.eq(combinationId));
         return query.fetch();
    }
}
