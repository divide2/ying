package com.divide2.product.service;

import com.divide2.core.basic.service.BasicService;
import com.divide2.product.model.ProductSpec;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/10
 */
public interface ProductSpecService extends BasicService<ProductSpec, String> {


    List<ProductSpec> listByProduct(String productId);
}
