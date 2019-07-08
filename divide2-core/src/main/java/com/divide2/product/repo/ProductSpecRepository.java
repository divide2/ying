package com.divide2.product.repo;

import com.divide2.product.model.ProductSpec;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/9
 */
public interface ProductSpecRepository extends JpaRepository<ProductSpec,String> {
    /**
     * 通过product删除
     * @param id productId
     */
    void deleteByProductId(String id);

    /**
     * 通过Product 查询
     * @param productId pid
     * @return x
     */
    List<ProductSpec> findByProductId(String productId);
}
