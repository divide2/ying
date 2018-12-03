package com.ying.product.model;

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
@Embeddable
public class ProductImage {
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
