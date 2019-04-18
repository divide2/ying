package com.divide2.order.dto;

import com.divide2.core.root.dto.DTO;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 * @date 2018/12/18
 */
@Data
public class OrderConfirmDTO implements DTO {
    @NotNull
    private Integer orderId;
}
