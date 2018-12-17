package com.ying.order.model;

import lombok.Data;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
public class OrderImpl implements IOrder{

    private Integer toId;

    private String toName;

    private Integer fromId;

    private String fromName;

    private String orderNo;
}
