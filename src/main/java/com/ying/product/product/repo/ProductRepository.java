package com.ying.product.product.repo;

import com.ying.product.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/8/16
 */
public interface ProductRepository  extends JpaRepository<Product,Integer> {


    /**
     * 获取用户的产品
     * @param userId userid
     * @param pageable pageable
     * @return product
     */
    Page<Product> findByFromId(Integer userId, Pageable pageable);
}
