package com.ying.product.repo.custom;

import com.ying.product.query.StockQuery;
import com.ying.product.vo.StockVO;
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
