package com.ying.product.product.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.product.product.dto.ProductAddDTO;
import com.ying.product.product.dto.ProductUpdateDTO;
import com.ying.product.product.model.Product;
import com.ying.product.product.repo.ProductRepository;
import com.ying.product.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Service
public class ProductServiceImpl extends SimpleBasicServiceImpl<Product, Integer, ProductRepository> implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findByUser(Integer userId, Pageable pageable) {
        Page<Product> products = productRepository.findByFromId(userId, pageable);
        return products;
    }


    @Override
    public void add(ProductAddDTO dto) {
        Product product = super.add(dto.toProduct());
    }


    @Override
    public void update(ProductUpdateDTO dto) {
        Product product = super.update(dto.toProduct());
    }


}
