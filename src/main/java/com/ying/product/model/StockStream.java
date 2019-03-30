package com.ying.product.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 库存流水
 * @author bvvy
 * @date 2019/3/6
 */
@Data
@Entity
@Table(name = "t_stock_stream")
public class StockStream {
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
    private String teamId;
    private String warehouseId;
    private String productId;
    private String productSpecId;
    /**
     * 出入库的方式
     */
    private String type;
    /**
     * 出入库数量  入库为正 出库为负
     */
    private Integer stream;

    /**
     * 出入库后总数
     */
    private Integer amount;

    private LocalDateTime createTime;

}
