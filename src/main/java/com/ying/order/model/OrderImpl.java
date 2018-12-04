package com.ying.order.model;

import lombok.Data;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
public class OrderImpl implements IOrder{

    private Integer warehouseId;

    private String warehouseName;

    private Integer fromCompanyId;

    private Integer toCompanyId;

    private String fromCompanyName;

    private String toCompanyName;

    private Integer toId;

    private String toName;

    private Integer fromId;

    private String fromName;

    private String orderNo;
}
