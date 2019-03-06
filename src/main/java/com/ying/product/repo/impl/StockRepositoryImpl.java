package com.ying.product.repo.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.ying.core.basic.custom.BasicCustomRepository;
import com.ying.core.basic.custom.impl.SimpleBasicCustomRepositoryImpl;
import com.ying.core.root.query.QueryManager;
import com.ying.product.model.QProduct;
import com.ying.product.model.QStock;
import com.ying.product.model.QWarehouse;
import com.ying.product.query.StockQuery;
import com.ying.product.repo.custom.StockRepositoryCustom;
import com.ying.product.vo.StockVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

/**
 * @author bvvy
 * @date 2018/12/10
 */
public class StockRepositoryImpl extends SimpleBasicCustomRepositoryImpl
        implements StockRepositoryCustom, BasicCustomRepository {

    private QWarehouse w = QWarehouse.warehouse;
    private QStock wp = QStock.stock;
    private QProduct p = QProduct.product;

    public StockRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    private QBean<StockVO> qBean = Projections.bean(StockVO.class, p.id, p.name, p.image, wp.amount);


    @Override
    // todo not neessag 记录teamId
    public Page<StockVO> findByTeam(String teamId, StockQuery stockQuery, Pageable pageable) {
        BooleanExpression predicate = QueryManager.resolvePredicate(stockQuery);
        JPAQuery<StockVO> query = createQuery().select(qBean)
                .from(w)
                .innerJoin(wp).on(w.id.eq(wp.warehouseId))
                .innerJoin(p).on(p.id.eq(wp.productId))
                .where(w.teamId.eq(teamId).and(predicate));
        return super.findPage(query, pageable);
    }

    @Override
    public StockVO getStock(String warehouseId, String productId) {
        return createQuery().select(qBean)
                .from(w).innerJoin(wp).on(w.id.eq(wp.warehouseId))
                .innerJoin(p).on(p.id.eq(wp.productId))
                .where(wp.warehouseId.eq(warehouseId).and(wp.productId.eq(productId))).fetchOne();
    }
}
