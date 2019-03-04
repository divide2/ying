package com.ying.product.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.product.model.ProductSpec;
import com.ying.product.repo.ProductSpecRepository;
import com.ying.product.service.ProductSpecService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/10
 */
@Service
public class ProductSpecServiceImpl extends SimpleBasicServiceImpl<ProductSpec,String, ProductSpecRepository> implements ProductSpecService {
    private final ProductSpecRepository productSpecRepository;

    public ProductSpecServiceImpl(ProductSpecRepository productSpecRepository) {
        this.productSpecRepository = productSpecRepository;
    }

    @Override
    public List<ProductSpec> listByProduct(String productId) {
        return productSpecRepository.findByProductId(productId);

    }
}
