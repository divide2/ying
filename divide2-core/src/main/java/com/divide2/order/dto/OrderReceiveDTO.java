package com.divide2.order.dto;

import com.divide2.core.root.dto.DTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 发货
 * @author bvvy
 * @date 2018/12/26
 */
@Data
public class OrderReceiveDTO implements DTO {

    @NotEmpty
    private String orderId;
    @NotEmpty
    private String warehouseId;

}
