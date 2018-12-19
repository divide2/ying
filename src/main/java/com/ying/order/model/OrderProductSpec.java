package com.ying.order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
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
@Table(name="o_order_product_spec")
public class OrderProductSpec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orderId;
    private Integer orderProductId;
    private String productName;
    private Integer productId;
    private String specName;
    private BigDecimal price;
    private Integer amount;
    private LocalDateTime createTime;

}
