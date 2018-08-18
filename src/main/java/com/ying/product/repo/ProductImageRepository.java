package com.ying.product.repo;

import com.ying.product.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/8/19
 */
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    /**
     *  通过产品id
     * @param productId product Id
     * @return product images
     */
    List<ProductImage> findByProductId(Integer productId);

    /**
     * 通过product Ids 获取图片
     * @param productIds product ids
     * @return product iamge
     */
    List<ProductImage> findByProductIdIn(List<Integer> productIds);


}
