package com.ying.product.product.model;

import lombok.Data;
import org.hibernate.annotations.Type;

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
     * 主要图片
     */
    @Type(type = "yes_no")
    private Boolean main;

    @Column(name = "order_num")
    private Integer orderNum;

}
