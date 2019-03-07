package com.ying.product.service;

import com.ying.product.vo.ProductVO;
import com.ying.team.vo.TeamVO;

import java.util.List;
import java.util.Map;

/**
 * @author bvvy
 * @date 2018/12/28
 */
public interface ProductInnerCollectService {
    List<ProductVO> listProductByIds(List<String> ids);

    Map<String, ProductVO> mapProductByIds(List<String> ids);
}
