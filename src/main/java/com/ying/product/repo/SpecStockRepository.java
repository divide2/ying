package com.ying.product.repo;

import com.ying.product.model.SpecStock;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2018/12/9
 */
public interface SpecStockRepository extends JpaRepository<SpecStock,String>{
    default SpecStock getByAllId(String warehouseId, String productId, String productSpecId){
        SpecStock wps = new SpecStock();
        wps.setWarehouseId(warehouseId);
        wps.setProductId(productId);
        wps.setProductSpecId(productSpecId);
        Optional<SpecStock> one = this.findOne(Example.of(wps));
        return one.orElse(null);
    }

    List<SpecStock> findByWarehouseId(String warehouseId);


    default Map<String, List<SpecStock>> mapByProductIds(String warehouseId) {
        return this.findByWarehouseId(warehouseId).stream().collect(Collectors.groupingBy(SpecStock::getProductId));
    }

    List<SpecStock> findByWarehouseIdAndProductId(String warehouseId, String productId);

    List<SpecStock>  findByProductId(String id);
}
