package com.ying.product.service.impl;

import com.ying.core.basic.service.impl.SimpleBasicServiceImpl;
import com.ying.product.model.Product;
import com.ying.product.repo.ProductRepository;
import com.ying.product.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Service
public class ProductServiceImpl extends SimpleBasicServiceImpl<Product, Integer, ProductRepository> implements ProductService {

}
