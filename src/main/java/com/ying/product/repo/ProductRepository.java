package com.ying.product.repo;

import com.ying.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2018/8/16
 */
public interface ProductRepository  extends JpaRepository<Product,String> {


    /**
     * 获取公司的商品
     *
     * @param teamId teamId
     * @return product
     */
    Page<Product> findByTeamIdAndEnabled(String teamId,Boolean enabled, Pageable pageable);

    default Page<Product> findByTeam(String teamId, Pageable pageable) {
        return findByTeamIdAndEnabled(teamId, true, pageable);
    }


    /**
     * 获取集合
     * @param productIds ids
     * @return products
     */
    List<Product> findByIdIn(List<String> productIds);

    default Map<String, Product> findByIds(List<String> productIds) {
        return this.findByIdIn(productIds).stream().collect(Collectors.toMap(Product::getId, product -> product));
    }
}
