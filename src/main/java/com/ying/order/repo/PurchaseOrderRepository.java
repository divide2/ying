package com.ying.order.repo;

import com.ying.order.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/2
 */
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}
