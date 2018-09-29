package com.ying.product.product.service.impl;

import com.ying.basis.tag.service.TagService;
import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.core.er.Loginer;
import com.ying.product.consumer.service.ConsumerService;
import com.ying.product.product.dto.ProductAddDTO;
import com.ying.product.product.dto.ProductCommentAddDTO;
import com.ying.product.product.dto.ProductUpdateDTO;
import com.ying.product.product.model.Product;
import com.ying.product.product.repo.ProductRepository;
import com.ying.product.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Service
public class ProductServiceImpl extends SimpleBasicServiceImpl<Product, Integer, ProductRepository> implements ProductService {

    private final ProductRepository productRepository;
    private final TagService tagService;
    private final ConsumerService consumerService;

    public ProductServiceImpl(ProductRepository productRepository, TagService tagService,
                              ConsumerService consumerService) {
        this.productRepository = productRepository;
        this.tagService = tagService;
        this.consumerService = consumerService;
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
        product.setFromAvatar(Loginer.avatar());
        super.add(product);
        tagService.add(Arrays.asList(product.getTags()));

    }


    @Override
    public void update(ProductUpdateDTO dto) {
        Product product = super.update(dto.toProduct());
    }

    @Override
    public void addComment(ProductCommentAddDTO comment) {
        consumerService.addComment(comment);
    }
}
