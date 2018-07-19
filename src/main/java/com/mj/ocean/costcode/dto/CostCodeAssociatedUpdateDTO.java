package com.mj.ocean.costcode.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @author zejun
 * @date 2018/7/17 10:29
 */
@Data
@ApiModel("新增成本代码关联数据")
public class CostCodeAssociatedUpdateDTO {

    @ApiModelProperty("成本服务，成本类别")
    @NotEmpty
    private String costService;

    @ApiModelProperty("柜型")
    @NotEmpty
    private String cabinetType;

    @ApiModelProperty("浮动金额")
    @NotEmpty
    private BigDecimal floatingAmount;

}
