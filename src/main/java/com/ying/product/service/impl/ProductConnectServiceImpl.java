package com.ying.product.service.impl;

import com.ying.product.model.Product;
import com.ying.product.model.ProductSpec;
import com.ying.product.repo.ProductRepository;
import com.ying.product.repo.ProductSpecRepository;
import com.ying.product.repo.StockRepository;
import com.ying.product.service.ProductConnectService;
import com.ying.product.vo.ProductVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/28
 */
@Service
public class ProductConnectServiceImpl implements ProductConnectService {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final ProductSpecRepository productSpecRepository;


    public ProductConnectServiceImpl(ProductRepository productRepository,
                                     StockRepository stockRepository,
                                     ProductSpecRepository productSpecRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.productSpecRepository = productSpecRepository;
    }

    @Override
    public ProductVO getProductVO(String productId) {
        Product product = productRepository.getOne(productId);
        return this.mergeProductSpecs(product);
    }

    private ProductVO mergeProductSpecs(Product product) {
        ProductVO vo = ProductVO.of(product);
        List<ProductSpec> productSpecs = productSpecRepository.findByProductId(product.getId());
        vo.setSpecs(productSpecs);
        return vo;
    }

    @Override
    public Product getProduct(String productId) {
        return productRepository.getOne(productId);
    }

    @Override
    public ProductSpec getProductSpec(String productSpecId) {
        return productSpecRepository.getOne(productSpecId);
    }

}
