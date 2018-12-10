package com.ying.product.repo;

import com.ying.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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

    /**
     * 获取公司的商品
     * @param companyId companyId
     * @return product
     */
    List<Product> findByCompanyId(Integer companyId);

    /**
     * 分页
     * @param companyId companyId
     * @param pageable page
     * @return x
     */
    Page<Product> findByCompanyId(Integer companyId, Pageable pageable);
}
