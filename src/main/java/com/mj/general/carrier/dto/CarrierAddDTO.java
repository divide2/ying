package com.mj.general.carrier.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @auther: zejun
 * @date: 2018/7/9 10:19
 */
@Data
@ApiModel("船公司新增")
public class CarrierAddDTO {

    @ApiModelProperty("船公司简称")
    @NotEmpty
    private String carrierCode;

    @ApiModelProperty("船公司中文名")
    @NotEmpty
    private String carrierCN;

    @ApiModelProperty("船公司英文名")
    @NotEmpty
    private String carrierEN;
}
