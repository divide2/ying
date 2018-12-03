package com.ying.order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 采购单里的产品
 * @author bvvy
 * @date 2018/12/2
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "o_purchase_order_product")
@Entity
public class PurchaseOrderProduct extends OrderImpl implements IOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productId;
    private String productName;
    private Integer purchaseOrderId;
    private LocalDateTime createTime;

}
