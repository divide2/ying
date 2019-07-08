package com.divide2.product.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 仓库和 产品的规格 关联表 算是库存的详细表
 *
 * @author bvvy
 * @date 2018/12/9
 */
@Data
@Entity
@Table(name = "p_spec_stock")
public class SpecStock {

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
    private String productSpecName;

    private Integer amount;
}
