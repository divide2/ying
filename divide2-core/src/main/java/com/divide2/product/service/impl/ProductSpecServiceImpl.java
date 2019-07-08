package com.divide2.product.service.impl;

import com.divide2.core.basic.service.impl.SimpleBasicServiceImpl;
import com.divide2.product.model.ProductSpec;
import com.divide2.product.repo.ProductSpecRepository;
import com.divide2.product.service.ProductSpecService;
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
