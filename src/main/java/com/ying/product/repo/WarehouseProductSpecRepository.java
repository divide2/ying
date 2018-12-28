package com.ying.product.repo;

import com.ying.product.model.WarehouseProductSpec;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author bvvy
 * @date 2018/12/9
 */
public interface WarehouseProductSpecRepository extends JpaRepository<WarehouseProductSpec,Integer>{
    default WarehouseProductSpec getByAllId(Integer warehouseId, Integer productId, Integer productSpecId){
        WarehouseProductSpec wps = new WarehouseProductSpec();
        wps.setWarehouseId(warehouseId);
        wps.setProductId(productId);
        wps.setProductSpecId(productSpecId);
        Optional<WarehouseProductSpec> one = this.findOne(Example.of(wps));
        return one.orElse(null);
    }

    List<WarehouseProductSpec> findByWarehouseId(Integer warehouseId);
}
