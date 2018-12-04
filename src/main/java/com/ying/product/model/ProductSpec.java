package com.ying.product.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 产品的规格价格 不同规格价格可能不同
 * @author bvvy
 * @date 2018/12/3
 */
@Entity
@Table(name = "p_product_spec")
@Data
public class ProductSpec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String specName;
    private Integer productId;
    private BigDecimal price;
}
