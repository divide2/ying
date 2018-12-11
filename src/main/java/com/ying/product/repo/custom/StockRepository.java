package com.ying.product.repo.custom;

import com.ying.product.query.StockQuery;
import com.ying.product.bo.StockBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/12/10
 */
public interface StockRepository {
    Page<StockBO> findByCompany(Integer companyId, StockQuery stockQuery, Pageable pageable);

}
