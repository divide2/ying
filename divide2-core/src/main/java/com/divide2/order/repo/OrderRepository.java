package com.divide2.order.repo;

import com.divide2.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author bvvy
 * @date 2018/12/15
 */
public interface OrderRepository extends JpaRepository<Order, String>, QuerydslPredicateExecutor<Order> {


}
