package com.ying.product.service;

import com.ying.core.basic.service.BasicService;
import com.ying.product.model.ProductSpec;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/10
 */
public interface ProductSpecService extends BasicService<ProductSpec, Integer> {


    List<ProductSpec> listByProduct(Integer productId);
}
