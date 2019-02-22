package com.ying.product.repo.custom;

import com.ying.product.query.StockQuery;
import com.ying.product.bo.StockBO;
import com.ying.product.vo.StockVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/12/10
 */
public interface StockRepository {

    Page<StockVO> findByTeam(String teamId, StockQuery stockQuery, Pageable pageable);

    StockVO getStock(Integer warehouseId, Integer productId);
}
