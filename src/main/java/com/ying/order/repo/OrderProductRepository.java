package com.ying.order.repo;

import com.ying.order.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/18
 */
public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
}
