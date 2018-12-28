package com.ying.product.service;

import com.ying.product.vo.ProductVO;

import java.util.List;
import java.util.Map;

/**
 * @author bvvy
 * @date 2018/12/28
 */
public interface ProductInnerCollectService {
    List<ProductVO> listProductByIds(List<Integer> ids);

    Map<Integer, ProductVO> mapProductByIds(List<Integer> ids);
}
