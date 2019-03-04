package com.ying.order.repo;

import com.ying.order.model.Order;
import com.ying.order.repo.custom.OrderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author bvvy
 * @date 2018/12/15
 */
public interface OrderRepository extends JpaRepository<Order, String>, OrderRepositoryCustom, QuerydslPredicateExecutor<Order> {


}
