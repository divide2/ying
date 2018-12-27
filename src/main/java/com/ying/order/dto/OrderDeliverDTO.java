package com.ying.order.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 发货
 * @author bvvy
 * @date 2018/12/26
 */
@Data
public class OrderDeliverDTO {

    @NotNull
    private Integer orderId;
    @NotNull
    private Integer warehouseId;
}
