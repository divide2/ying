package com.ying.order.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
@Entity
@Table(name = "o_sell_order")
public class SellOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer orderId;

    private Integer toId;

    private Integer fromId;

    private String fromName;

    private String toName;

}
