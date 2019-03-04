package com.ying.order.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
@ApiModel("采购单")
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO {
    private String orderId;

    @NotNull
    private Integer fromId;

    @NotNull
    private Integer toId;
}

