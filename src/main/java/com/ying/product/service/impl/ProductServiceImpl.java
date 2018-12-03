package com.ying.product.service.impl;

import com.ying.basis.service.TagService;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.er.Loginer;
import com.ying.product.dto.ProductAddDTO;
import com.ying.product.dto.ProductUpdateDTO;
import com.ying.product.model.Product;
import com.ying.product.repo.ProductRepository;
import com.ying.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Service
public class ProductServiceImpl extends SimpleBasicServiceImpl<Product, Integer, ProductRepository> implements ProductService {

    private final ProductRepository productRepository;
    private final TagService tagService;

    public ProductServiceImpl(ProductRepository productRepository, TagService tagService) {
        this.productRepository = productRepository;
        this.tagService = tagService;
    }

    @Override
    public Page<Product> findByUser(Integer userId, Pageable pageable) {
        Page<Product> products = productRepository.findByFromId(userId, pageable);
        return products;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ProductAddDTO dto) {
        Product product = dto.toProduct();
        product.setFromId(Loginer.userId());
        product.setFromName(Loginer.username());
        super.add(product);

    }


    @Override
    public void update(ProductUpdateDTO dto) {
    }

}
