package com.ying.product.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 产品图片
 *
 * @author bvvy
 * @date 2018/8/18
 */
@Data
@Entity
@Table(name = "p_product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 图片地址
     */
    private String url;
    /**
     * 图片大小
     */
    private Integer size;
    /**
     * 图片宽度
     */
    private Integer width;
    /**
     * 图片高度
     */
    private Integer height;
    /**
     * 图片类型
     */
    private String type;
    /**
     * 主要图片
     */
    private Boolean main;

    @Column(name = "order_num")
    private Integer orderNum;

}
