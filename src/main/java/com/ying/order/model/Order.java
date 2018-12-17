package com.ying.order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * 订单 用于过度的
 * @author bvvy
 * @date 2018/12/2
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "o_order")
public class Order extends OrderImpl implements IOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 定金
     */
    private BigDecimal earnestMoney;

    /**
     * 尾款
     */
    private BigDecimal balancePayment;

    private LocalDateTime createTime;

    /**
     * 交付日期
     */
    private LocalDate deliveryDate;

    private String remarks;

    private String attachment;

    private String status;
}
