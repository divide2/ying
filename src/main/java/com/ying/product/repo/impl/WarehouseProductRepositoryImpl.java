package com.ying.product.repo.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.ying.core.basic.custom.BasicCustomRepository;
import com.ying.core.basic.custom.impl.SimpleBasicCustomRepositoryImpl;
import com.ying.product.model.QWarehouse;
import com.ying.product.model.QWarehouseProduct;
import com.ying.product.query.StockQuery;
import com.ying.product.bo.StockBO;
import com.ying.product.repo.custom.StockRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author bvvy
 * @date 2018/12/10
 */
public class WarehouseProductRepositoryImpl extends SimpleBasicCustomRepositoryImpl
        implements StockRepository, BasicCustomRepository {

    public WarehouseProductRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Page<StockBO> findByCompany(Integer companyId, StockQuery stockQuery, Pageable pageable) {
        QWarehouse warehouse = QWarehouse.warehouse;
        QWarehouseProduct warehouseProduct = QWarehouseProduct.warehouseProduct;
        JPAQuery<StockBO> query = new JPAQuery<>(entityManager).select(Projections.bean(StockBO.class,
                warehouse.id.as("warehouseId"), warehouse.name.as("warehouseName"),
                warehouse.type.as("warehouseType"), warehouseProduct.productId, warehouseProduct.amount))
                .from(warehouse).leftJoin(warehouseProduct).on(warehouse.id.eq(warehouseProduct.warehouseId))
                .where(warehouse.companyId.eq(companyId).and(warehouse.type.eq(stockQuery.getWarehouseType())));

        return super.findPage(query, pageable);
    }
}
