package com.ying.product.dto;

import com.ying.product.model.ProductComposite;
import com.ying.product.model.ProductSpec;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Data
@ApiModel("产品添加数据")
public class ProductDTO {

    private Integer id;

    private Integer warehouseId;
    /**
     * 名称
     */
    private String name;

    /**
     * 图片
     */
    private String image;

    /**
     * 备注 描述
     */
    private String remarks;


    private List<ProductSpec> specs;

    private List<ProductComposite> composites;
}
