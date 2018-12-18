package com.ying.order.repo;

import com.ying.order.model.OrderProductSpec;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/18
 */
public interface OrderProductSpecRepository extends JpaRepository<OrderProductSpec,Integer> {
}
