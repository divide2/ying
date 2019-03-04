package com.ying.order.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
@Entity
@Table(name = "o_sell_order")
public class SellOrder {
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

    private String orderId;

    private Integer toId;

    private Integer fromId;

    private String fromName;

    private String toName;

}
