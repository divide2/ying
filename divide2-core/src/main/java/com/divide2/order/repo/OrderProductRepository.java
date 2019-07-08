package com.divide2.order.repo;

import com.divide2.order.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/18
 */
public interface OrderProductRepository extends JpaRepository<OrderProduct, String> {
}
