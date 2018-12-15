package com.ying.order.repo;

import com.ying.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/15
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {


}
