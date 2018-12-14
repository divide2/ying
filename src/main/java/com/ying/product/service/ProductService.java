package com.ying.product.service;

import com.ying.core.basic.service.BasicService;
import com.ying.product.dto.ProductDTO;
import com.ying.product.dto.ProductUpdateDTO;
import com.ying.product.model.Product;
import com.ying.product.vo.ProductVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/8/16
 */
public interface ProductService extends BasicService<Product,Integer> {

    Page<ProductVO> findByUserCompany(Integer userId, Pageable pageable);

    /**
     * 获取用户的产品
     * @param userId userid
     * @param pageable pageable
     * @return products
     */
    Page<ProductVO> findByUser(Integer userId, Pageable pageable);

    /**
     * 获取公司下的商品
     * @param companyId companyid
     * @return x
     */
    Page<ProductVO> findByCompany(Integer companyId, Pageable pageable);

    /**
     * 添加
     * @param dto dto
     */
    void add(ProductDTO dto);

    /**
     * 修改
     * @param dto dto
     */
    void update(ProductUpdateDTO dto);


    /**
     * 获取详细信息
     * @param pageable 分页
     * @return vo
     */
    Page<ProductDTO> findInfo(Pageable pageable);

}
