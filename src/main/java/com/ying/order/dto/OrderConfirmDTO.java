package com.ying.order.dto;

import com.ying.core.root.dto.DTO;
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
