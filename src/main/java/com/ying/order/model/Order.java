package com.ying.order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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


}
