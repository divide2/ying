package com.ying.product.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author bvvy
 * @date 2018/12/4
 */
@Entity
@Table(name = "o_warehouse")
@Data
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer companyId;
    /**
     * parts P end product E
     */
    private String type;
    private String name;

    /**
     * 产品总数
     */
    private Integer productAmount;
}
