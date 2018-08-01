package com.mj.general.carrier.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zejun
 * @date 2018/7/9 14:28
 */
@Data
@ApiModel("船公司修改")
public class CarrierUpdateDTO {

    @ApiModelProperty("船公司id")
    @NotNull
    private Integer id;

    @ApiModelProperty("船公司简称")
    @NotEmpty
    private String carrierCode;

    @ApiModelProperty("船公司中文名")
    private String carrierCN;

    @ApiModelProperty("船公司英文名")
    private String carrierEN;
}
