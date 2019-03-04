package com.ying.order.repo;

import com.ying.order.model.SellOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/18
 */
public interface SellOrderRepository extends JpaRepository<SellOrder, String> {

    Page<SellOrder> findByFromId(Integer userId, Pageable pageable);
}
