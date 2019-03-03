package com.ying.product.repo;

import com.ying.product.model.WarehouseProduct;
import com.ying.product.repo.custom.StockRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/9
 */
public interface WarehouseProductRepository extends JpaRepository<WarehouseProduct, Integer>, StockRepository {


    WarehouseProduct getByWarehouseIdAndProductId(String warehouseId, Integer productId);


    WarehouseProduct getByWarehouseId(Integer warehouseId);
}
