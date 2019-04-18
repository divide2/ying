package com.divide2.product.service;

import com.divide2.product.bo.UnitBO;
import com.divide2.product.dto.UnitDTO;
import com.divide2.product.model.Product;
import com.divide2.product.model.ProductSpec;
import com.divide2.product.vo.ProductVO;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/28
 */
public interface ProductConnectService {
    ProductVO getProductVO(String productId);

    Product getProduct(String productId);

    ProductSpec getProductSpec(String productSpecId);


    void saveOrUpdateUnit(String teamId,UnitBO bo);

    void saveOrUpdateUnits(String teamId, List<UnitBO> bos);

    List<UnitDTO> getUnits(String[] unitIds);
}
