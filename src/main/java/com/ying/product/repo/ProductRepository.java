package com.ying.product.repo;

import com.ying.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/8/16
 */
public interface ProductRepository  extends JpaRepository<Product,Integer> {


}
