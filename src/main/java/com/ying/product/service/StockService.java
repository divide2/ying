package com.ying.product.service;

import com.ying.product.dto.StockDTO;
import com.ying.product.query.StockQuery;
import com.ying.product.bo.StockBO;
import com.ying.product.vo.StockVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/12/9
 */
public interface StockService {
    void add(StockDTO dto);

    Page<StockVO> findByCompany(StockQuery stockQuery, Pageable pageable);

    Page<StockBO> find(StockQuery stockQuery);

}
