package com.ying.order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 采购单
 * @author bvvy
 * @date 2018/12/2
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "o_purchase_order")
@Data
public class PurchaseOrder extends OrderImpl implements IOrder {

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

    private LocalDateTime createTime;

    private Integer toId;

    private String toName;

    private Integer fromId;

    private String fromName;


}
