package com.divide2.product.repo;

import com.divide2.product.model.Stock;
import com.divide2.product.repo.custom.StockRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/9
 */
public interface StockRepository extends JpaRepository<Stock, String>, StockRepositoryCustom {


    Stock getByWarehouseIdAndProductId(String warehouseId, String productId);


    Stock getByWarehouseId(String warehouseId);
}
