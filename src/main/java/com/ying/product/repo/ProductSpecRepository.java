package com.ying.product.repo;

import com.ying.product.model.ProductSpec;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bvvy
 * @date 2018/12/9
 */
public interface ProductSpecRepository extends JpaRepository<ProductSpec,Integer> {
    /**
     * 通过product删除
     * @param id productId
     */
    void deleteByProductId(Integer id);
}
