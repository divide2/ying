package com.divide2.product.repo.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.divide2.core.basic.custom.BasicCustomRepository;
import com.divide2.core.basic.custom.impl.SimpleBasicCustomRepositoryImpl;
import com.divide2.core.root.query.QueryManager;
import com.divide2.product.model.QProduct;
import com.divide2.product.model.QStock;
import com.divide2.product.model.QWarehouse;
import com.divide2.product.query.StockQuery;
import com.divide2.product.repo.custom.StockRepositoryCustom;
import com.divide2.product.vo.StockVO;
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
