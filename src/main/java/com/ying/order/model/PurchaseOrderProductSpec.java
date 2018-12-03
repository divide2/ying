package com.ying.order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单最后一层 是规格级别的
 * @author bvvy
 * @date 2018/12/2
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name="o_purchase_order_product_spec")
public class PurchaseOrderProductSpec extends OrderImpl implements IOrder{

    private Integer id;
    private Integer purchaseOrderId;
    private Integer purchaseOrderProductId;
    private String productName;
    private Integer productId;
    private String specName;
    private BigDecimal price;
    private Integer amount;
    private LocalDateTime createTime;

}
