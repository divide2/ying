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
    private String orderId;
    @NotNull
    private String warehouseId;
}
