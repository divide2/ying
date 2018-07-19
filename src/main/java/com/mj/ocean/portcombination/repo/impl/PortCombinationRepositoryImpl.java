package com.mj.ocean.portcombination.repo.impl;

import com.mj.general.carrier.model.QCarrier;
import com.mj.general.port.model.QPort;
import com.mj.ocean.portcombination.dto.CombinationQueryDTO;
import com.mj.ocean.portcombination.model.QPortCombination;
import com.mj.ocean.portcombination.model.QPortCombinationAssociated;
import com.mj.ocean.portcombination.repo.custom.PortCombinationRepositoryCustom;
import com.mj.ocean.portcombination.vo.CombinationVO;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLTemplates;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author zejun
 * @date 2018/7/16 09:46
 */
public class PortCombinationRepositoryImpl implements PortCombinationRepositoryCustom {

    private final EntityManager entityManager;

    public PortCombinationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<CombinationVO> findAll(CombinationQueryDTO combinationQueryDTO, Pageable pageable) {
        QPort qPort = QPort.port;
        QPortCombination portCombination = QPortCombination.portCombination;
        QCarrier carrier = QCarrier.carrier;
        QPortCombinationAssociated portCombinationAssociated = QPortCombinationAssociated.portCombinationAssociated;
        SQLTemplates sqlTemplates = new MySQLTemplates();
        List<String> params = new ArrayList<>();
//        JPASQLQuery<CombinationVO> query = new JPASQLQuery<>(entityManager,sqlTemplates);
        String sql = "SELECT\n" +
                "ofpc.id, \n" +
                "ofpc.combination_name as combinationName, \n" +
                "ofpc.enabled as enabled, \n" +
                "GROUP_CONCAT(DISTINCT gc.carrier_code) as carrierCode,\n" +
                "GROUP_CONCAT(DISTINCT gp.port_code) as portCodes,\n" +
                "GROUP_CONCAT(DISTINCT gp.porten) as portENs,\n" +
                "GROUP_CONCAT(DISTINCT gp.countryen) as countryENs\n" +
                "from \n" +
                "ocean_fc_port_combination ofpc LEFT JOIN ocean_fc_port_combination_associated pczh on ofpc.id = pczh.combination_id \n" +
                "left join \n" +
                "general_carrier gc on gc.id = pczh.carrier_id\n" +
                "left join \n" +
                "general_port gp on gp.id = pczh.port_id";
        if (StringUtils.isNotEmpty(combinationQueryDTO.getCombinationName())) {
//            sql += " where ofpc.combination_name = '" + combinationQueryDTO.getCombinationName()+"'";
            sql += " where ofpc.combination_name like ?";
            params.add("%"+combinationQueryDTO.getCombinationName()+"%");
        }
        if (StringUtils.isNotEmpty(combinationQueryDTO.getCarrierCode())) {
            sql += " where gc.carrier_code = ?";
            params.add(combinationQueryDTO.getCarrierCode());
        }
        sql += " group by ofpc.id ";
        Query query = entityManager.createNativeQuery(sql);
        for (int i = 1; i <= params.size(); i++) {
            query.setParameter(i, params.get(i-1));
        }
        String countSql = "select count(*) from (" + sql + ")";
        Query countQuery = entityManager.createNativeQuery(countSql);

//        query = query.select(constructor(CombinationVO.class,portCombination.id,portCombination.combinationName,portCombination.enabled,
//                SQLExpressions.groupConcat(carrier.carrierCode),SQLExpressions.groupConcat(qPort.portCode),
//                SQLExpressions.groupConcat(qPort.portEN),SQLExpressions.groupConcat(qPort.countryEN)))
//                .from(portCombination)
//                .leftJoin(portCombinationAssociated)
//                .on(portCombination.id.eq(portCombinationAssociated.combinationId))
//                .leftJoin(carrier)
//                .on(carrier.id.eq(portCombinationAssociated.carrierId))
//                .leftJoin(qPort)
//                .on(qPort.id.eq(portCombinationAssociated.portId));
//
//        if (StringUtils.isNotEmpty(combinationQueryDTO.getCarrierCode())){
//            query = query.where(carrier.carrierCode.eq(combinationQueryDTO.getCarrierCode()));
//        }
//        if (StringUtils.isNotEmpty(combinationQueryDTO.getCombinationName())){
//            query = query.where(portCombination.combinationName.eq(combinationQueryDTO.getCombinationName()));
//        }
//        query = query.where(portCombination.deleted.eq('N').and(portCombination.enabled.eq('Y')))
//                .groupBy(portCombination.id);
        List<Object[]> results = query.getResultList();
        List<CombinationVO> combinationVOS = results.stream()
                .map(o -> new CombinationVO((Integer) o[0], (String) o[1], (Character)o[2], (String) o[3], (String) o[4], (String)o[5], (String)o[6])).collect(Collectors.toList());
        long count = countQuery.getFirstResult();
        return new PageImpl<>(combinationVOS, pageable, count);
    }
}
