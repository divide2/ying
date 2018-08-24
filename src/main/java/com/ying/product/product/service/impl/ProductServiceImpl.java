package com.ying.product.product.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.product.product.dto.ProductAddDTO;
import com.ying.product.product.dto.ProductImageDTO;
import com.ying.product.product.dto.ProductUpdateDTO;
import com.ying.product.product.model.Product;
import com.ying.product.product.model.ProductImage;
import com.ying.product.product.repo.ProductImageRepository;
import com.ying.product.product.repo.ProductRepository;
import com.ying.product.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Service
public class ProductServiceImpl extends SimpleBasicServiceImpl<Product, Integer, ProductRepository> implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    @Override
    public Page<Product> findByUser(Integer userId, Pageable pageable) {
        Page<Product> products = productRepository.findByFromId(userId, pageable);


        return products;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ProductAddDTO dto) {
        Product product = super.add(dto.toProduct());
        this.saveImages(dto.getImages(), product.getId());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProductUpdateDTO dto) {
        Product product = super.update(dto.toProduct());
        productImageRepository.deleteByProductId(product.getId());
        this.saveImages(dto.getImages(),product.getId());
    }

    private void saveImages(List<ProductImageDTO> images,Integer productId) {
        images.forEach(it -> {
            ProductImage pi = new ProductImage();
            pi.setProductId(productId);
            pi.setUrl(it.getUrl());
            pi.setMain(it.getMain());
            productImageRepository.save(pi);
        });
    }

}
