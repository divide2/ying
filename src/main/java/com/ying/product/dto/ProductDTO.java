package com.ying.product.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/8/16
 */
@Data
@ApiModel("产品添加数据")
public class ProductDTO {

    private Integer id;
    /**
     * 名称
     */
    private String name;

    /**
     * 图片
     */
    private String image;

    /**
     * 采购价
     */
    private String purchasePrice;

    /**
     * 销售价
     */
    private String sellPrice;


    /**
     * 备注 描述
     */
    private String remarks;

}
