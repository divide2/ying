package com.mj.ocean.quotation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * @author zejun
 * @date 2018/7/19 10:38
 */
@Data
@ApiModel("常规报价查询")
public class QuotationQueryDTO {

    @ApiModelProperty("船公司")
    public String carrierCode;

    @ApiModelProperty("起运港口")
    public String portShipment;

    @ApiModelProperty("目的港口")
    public String portDestination;

    @ApiModelProperty("航线代码")
    public String routeCode;

    @ApiModelProperty("有效")
    public Character enabled;

    @ApiModelProperty("有效开始时间")
    private LocalDateTime effectiveStartTime;

    @ApiModelProperty("有效结束时间")
    private LocalDateTime effectiveEndTime;
}
