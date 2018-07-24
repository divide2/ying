package com.mj.ocean.quotation.repo.impl;

import com.mj.ocean.quotation.dto.QuotationCallHistory;
import com.mj.ocean.quotation.dto.QuotationQueryDTO;
import com.mj.ocean.quotation.model.QQuotation;
import com.mj.ocean.quotation.model.QQuotationCost;
import com.mj.ocean.quotation.repo.custom.QuotationRepositoryCustom;
import com.mj.ocean.quotation.vo.QuotationVO;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.querydsl.core.types.Projections.constructor;

/**
 * @author zejun
 * @date 2018/7/19 11:14
 */
public class QuotationRepositoryImpl implements QuotationRepositoryCustom {
    private final EntityManager entityManager;

    public QuotationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<QuotationVO> findAll(String costServiceCode, QuotationQueryDTO quotationQueryDTO, Pageable pageable) {
        QQuotation quotation = QQuotation.quotation;
        QQuotationCost quotationCost = QQuotationCost.quotationCost;
        JPAQuery<QuotationVO> query = new JPAQuery<>(entityManager);
        query = query.select(constructor(QuotationVO.class, quotation.id, quotation.carrierId, quotation.carrierCode,
                quotation.routeId, quotation.routeCode, quotation.portShipmentId, quotation.portShipment,
                quotation.portDestinationId, quotation.portDestination, quotation.closingTime,
                quotation.sailingTime, quotation.transitPort, quotation.voyage, quotation.currency,
                quotation.remarks, quotation.yermValidity, quotation.costId, quotation.costCode,
                quotationCost.cabinetType)).from(quotation)
                .leftJoin(quotationCost).on(quotation.id.eq(quotationCost.quotationId));
        if (StringUtils.isNotEmpty(quotationQueryDTO.getCarrierCode())) {
            query = query.where(quotation.carrierCode.like("%" + quotationQueryDTO.getCarrierCode() + "%"));
        }
        if (StringUtils.isNotEmpty(quotationQueryDTO.getPortShipment())) {
            query = query.where(quotation.portShipment.like("%" + quotationQueryDTO.getPortShipment() + "%"));
        }
        if (StringUtils.isNotEmpty(quotationQueryDTO.getPortDestination())) {
            query = query.where(quotation.portDestination.like("%" + quotationQueryDTO.getPortDestination() + "%"));
        }
        if (StringUtils.isNotEmpty(quotationQueryDTO.getRouteCode())) {
            query = query.where(quotation.routeCode.like("%" + quotationQueryDTO.getRouteCode() + "%"));
        }
        if (StringUtils.isNotEmpty(quotationQueryDTO.getEnabled().toString())) {
            query = query.where(quotation.enabled.eq(quotationQueryDTO.getEnabled()));
        }
        if (StringUtils.isNotEmpty(quotationQueryDTO.getYermValidity())) {
            query = query.where(quotation.yermValidity.eq(quotationQueryDTO.getYermValidity()));
        }
        query = query.where(quotationCost.costService.eq("此处是成本服务，编码暂时未确定")
                    .and(quotationCost.costServiceCode.eq("此处是标准报价或特殊报价，编码未定。costServiceCode"))
                )
                .groupBy(quotation.id).limit(pageable.getPageSize()).offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    @Override
    public List<QuotationVO> callHistory(QuotationCallHistory quotationCallHistory) {
        QQuotation quotation = QQuotation.quotation;
        QQuotationCost quotationCost = QQuotationCost.quotationCost;
        JPAQuery<QuotationVO> query = new JPAQuery<>(entityManager);
        query = query.select(constructor(QuotationVO.class, quotation.id, quotation.carrierId, quotation.carrierCode,
                quotation.routeId, quotation.routeCode, quotation.portShipmentId, quotation.portShipment,
                quotation.portDestinationId, quotation.portDestination, quotation.closingTime,
                quotation.sailingTime, quotation.transitPort, quotation.voyage, quotation.currency,
                quotation.remarks, quotation.yermValidity, quotation.costId, quotation.costCode,
                quotationCost.cabinetType))
                .from(quotation)
                .leftJoin(quotationCost).on(quotation.id.eq(quotationCost.quotationId))
                .where(quotation.carrierId.eq(quotationCallHistory.getCarrierId())
                        .and(quotation.portShipmentCombinationId.eq(quotationCallHistory.getPortShipmentCombinationId()))
                        .and(quotation.portDestinationCombinationId.eq(quotationCallHistory.getPortDestinationCombinationId()))
                        .and(quotation.createdDate.eq(quotation.createdDate.max()))
                )
                .groupBy(quotation.id);
        return query.fetch();
    }
}
