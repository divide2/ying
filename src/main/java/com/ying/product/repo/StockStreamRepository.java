package com.ying.product.repo;

import com.ying.product.model.StockStream;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2019/3/6
 */
public interface StockStreamRepository extends JpaRepository<StockStream, String> {

}
