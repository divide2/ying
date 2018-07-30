package com.mj.ocean.quotation.repo.impl;

import com.mj.ocean.quotation.dto.QuotationCallHistory;
import com.mj.ocean.quotation.dto.QuotationQueryDTO;
import com.mj.ocean.quotation.model.QQuotation;
import com.mj.ocean.quotation.model.Quotation;
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
        //todo 根据登陆用户获取客户公司id
        int companyId = 1;
        QQuotation quotation = QQuotation.quotation;
        JPAQuery<QuotationVO> query = new JPAQuery<>(entityManager);
        query = query.select(constructor(QuotationVO.class, quotation.id, quotation.carrierId, quotation.carrierCode,
                quotation.routeId, quotation.routeCode, quotation.portShipmentId, quotation.portShipment,
                quotation.portDestinationId, quotation.portDestination, quotation.etc,
                quotation.etd, quotation.transitPort, quotation.tt, quotation.currency,
                quotation.remarks, quotation.effectiveStartTime, quotation.effectiveEndTime,
                quotation.costId, quotation.costCode, quotation.costServiceCode,
                quotation.enabled)).from(quotation);
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
        if (quotationQueryDTO.getEnabled() != null) {
            query = query.where(quotation.enabled.eq(quotationQueryDTO.getEnabled()));
        }
        if (StringUtils.isNotEmpty(quotationQueryDTO.getEffectiveStartTime().toString())
                && StringUtils.isNotEmpty(quotationQueryDTO.getEffectiveEndTime().toString())) {
            query = query.where(quotation.effectiveStartTime.between(quotationQueryDTO.getEffectiveStartTime(),quotationQueryDTO.getEffectiveEndTime()));
        }
        query = query.where(quotation.costServiceCode.eq(costServiceCode)
                    .and(quotation.companyId.eq(companyId))
                )
                .limit(pageable.getPageSize()).offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    @Override
    public List<QuotationVO> callHistory(QuotationCallHistory quotationCallHistory,Quotation qt) {
        //todo 根据登陆用户获取客户公司id
        int companyId = 1;
        QQuotation quotation = QQuotation.quotation;
        JPAQuery<QuotationVO> query = new JPAQuery<>(entityManager);
        query = query.select(constructor(QuotationVO.class, quotation.id, quotation.carrierId, quotation.carrierCode,
                quotation.routeId, quotation.routeCode, quotation.portShipmentId, quotation.portShipment,
                quotation.portDestinationId, quotation.portDestination, quotation.etc,
                quotation.etd, quotation.transitPort, quotation.tt, quotation.currency,
                quotation.remarks, quotation.effectiveStartTime, quotation.effectiveEndTime,
                quotation.costId, quotation.costCode, quotation.costServiceCode,
                quotation.enabled)).from(quotation)
                .where(quotation.carrierId.eq(quotationCallHistory.getCarrierId())
                        .and(quotation.portShipmentCombinationId.eq(quotationCallHistory.getPortShipmentCombinationId()))
                        .and(quotation.portDestinationCombinationId.eq(quotationCallHistory.getPortDestinationCombinationId()))
                        .and(quotation.costServiceCode.eq("general"))
                        .and(quotation.companyId.eq(companyId))
                );
        if (qt != null) {
            query = query.where(quotation.createdDate.eq(qt.getCreatedDate()));
        }
        return query.fetch();
    }
}
