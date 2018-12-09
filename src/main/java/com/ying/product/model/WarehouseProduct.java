package com.ying.product.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 仓库和产品的关联表 就是库存表
 * @author bvvy
 * @date 2018/12/9
 */
@Data
@Table(name = "p_warehouse_product")
@Entity
public class WarehouseProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer warehouseId;
    private Integer productId;
    private Integer amount;

}
