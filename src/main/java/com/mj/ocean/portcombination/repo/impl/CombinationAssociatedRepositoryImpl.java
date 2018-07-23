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
    public CombinationAssociatedVO findByCombinationId(Integer combinationId) {
        QPort port = QPort.port;
        QCarrier carrier = QCarrier.carrier;
        QPortCombination portCombination = QPortCombination.portCombination;
        QPortCombinationAssociated portCombinationAssociated = QPortCombinationAssociated.portCombinationAssociated;

        JPAQuery<CombinationAssociatedVO> query = new JPAQuery<>(entityManager);
        query = query.select(constructor(CombinationAssociatedVO.class,portCombination.id,portCombination.combinationName,
                portCombination.enabled,carrier.id,carrier.carrierCode,SQLExpressions.groupConcat(port.id.stringValue()),
                SQLExpressions.groupConcat(port.portCode),SQLExpressions.groupConcat(port.portEN),
                SQLExpressions.groupConcat(port.countryEN))).from(portCombination)
                .leftJoin(portCombinationAssociated)
                .on(portCombination.id.eq(portCombinationAssociated.combinationId))
                .leftJoin(carrier)
                .on(carrier.id.eq(portCombinationAssociated.carrierId))
                .leftJoin(port)
                .on(port.id.eq(portCombinationAssociated.portId))
                .where(portCombination.enabled.eq('Y'))
                .groupBy(portCombination.id);
        return query.fetchOne();
    }
}
