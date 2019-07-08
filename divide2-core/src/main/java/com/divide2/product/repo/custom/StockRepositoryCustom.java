package com.divide2.product.repo.custom;

import com.divide2.product.query.StockQuery;
import com.divide2.product.vo.StockVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/12/10
 */
public interface StockRepositoryCustom {

    Page<StockVO> findByTeam(String teamId, StockQuery stockQuery, Pageable pageable);

    StockVO getStock(String warehouseId, String productId);
}
