package com.ying.product.repo;

import com.ying.product.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/9
 */
public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    List<Warehouse> findByCompanyId(Integer companyId);
}
