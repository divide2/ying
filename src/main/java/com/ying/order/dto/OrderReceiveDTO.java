package com.ying.order.dto;

import com.ying.core.root.dto.DTO;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 发货
 * @author bvvy
 * @date 2018/12/26
 */
@Data
public class OrderReceiveDTO implements DTO {

    @NotNull
    private String orderId;
    @NotNull
    private String warehouseId;
}
