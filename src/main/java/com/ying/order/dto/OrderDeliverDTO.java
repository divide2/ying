package com.ying.order.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 发货
 * @author bvvy
 * @date 2018/12/26
 */
@Data
public class OrderDeliverDTO {

    @NotEmpty
    private String orderId;
    @NotEmpty
    private String warehouseId;
}
