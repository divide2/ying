package com.divide2.order.repo;

import com.divide2.order.model.OrderProductSpec;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/18
 */
public interface OrderProductSpecRepository extends JpaRepository<OrderProductSpec, String> {

    /**
     * by ordreId
     *
     * @param orderId order id
     * @return list
     */
    List<OrderProductSpec> findByOrderId(String orderId);

}
