package com.ying.product.service;

import com.ying.product.model.Product;
import com.ying.product.model.ProductSpec;
import com.ying.product.vo.ProductVO;

/**
 * @author bvvy
 * @date 2018/12/28
 */
public interface ProductConnectService {
    ProductVO getProductVO(String productId);

    Product getProduct(String productId);

    ProductSpec getProductSpec(String productSpecId);

}
