package com.ying.product.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 仓库和 产品的规格 关联表 算是库存的详细表
 *
 * @author bvvy
 * @date 2018/12/9
 */
@Data
@Entity
@Table(name = "p_warehouse_product_spec")
public class WarehouseProductSpec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer warehouseId;
    private Integer productId;
    private Integer productSpecId;

    private Integer amount;
}
