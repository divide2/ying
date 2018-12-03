package com.ying.product.service;

import com.ying.core.basic.service.BasicService;
import com.ying.product.dto.ProductAddDTO;
import com.ying.product.dto.ProductCommentAddDTO;
import com.ying.product.dto.ProductUpdateDTO;
import com.ying.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author bvvy
 * @date 2018/8/16
 */
public interface ProductService extends BasicService<Product,Integer> {

    /**
     * 获取用户的产品
     * @param userId userid
     * @param pageable pageable
     * @return products
     */
    Page<Product> findByUser(Integer userId, Pageable pageable);

    /**
     * 添加
     * @param dto dto
     */
    void add(ProductAddDTO dto);

    /**
     * 修改
     * @param dto dto
     */
    void update(ProductUpdateDTO dto);


}
