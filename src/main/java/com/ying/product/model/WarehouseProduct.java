package com.ying.product.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String warehouseId;
    private Integer productId;
    private Integer amount;
    /**
     * 最后一次入库时间
     */
    private LocalDateTime lastTime;
}
