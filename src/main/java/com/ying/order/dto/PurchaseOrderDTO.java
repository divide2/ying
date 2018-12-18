package com.ying.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/12/2
 */
@Data
@ApiModel("采购单")
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO {
    private Integer orderId;

    @NotNull
    private Integer fromId;

    @NotNull
    private Integer toId;
}

