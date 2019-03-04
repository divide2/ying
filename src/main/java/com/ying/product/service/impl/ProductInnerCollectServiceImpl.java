package com.ying.product.service.impl;

import com.ying.product.service.ProductInnerCollectService;
import com.ying.product.service.ProductService;
import com.ying.product.vo.ProductVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author bvvy
 * @date 2018/12/28
 */
@Service
public class ProductInnerCollectServiceImpl implements ProductInnerCollectService {

    private final ProductService productService;

    public ProductInnerCollectServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<ProductVO> listProductByIds(List<String> ids) {
        return productService.findByIds(ids);
    }
    @Override
    public Map<String, ProductVO> mapProductByIds(List<String> ids) {
        return productService.findByIds(ids).stream().collect(Collectors.toMap(ProductVO::getId, product -> product));
    }


}
