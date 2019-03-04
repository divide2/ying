package com.ying.product.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(
            name = "custom-uuid",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private String id;

    private String warehouseId;
    private String productId;
    private Integer amount;
    /**
     * 最后一次入库时间
     */
    private LocalDateTime lastTime;
}
