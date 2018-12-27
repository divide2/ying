package com.ying.order.vo;

import lombok.Data;

/**
 * @author bvvy
 * @date 2018/12/26
 */
@Data
public class OrderProductVO {

    private Integer orderId;
    private Integer productId;
    private String productName;
    private Integer productSpecId;
    private Integer specName;
    private Integer amount;

}
