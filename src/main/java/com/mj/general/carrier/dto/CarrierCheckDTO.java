package com.mj.general.carrier.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/12 09:17
 */
@Data
@ApiModel("验证字段")
public class CarrierCheckDTO {

    @ApiModelProperty("船公司简称")
    private String carrierCode;

    @ApiModelProperty("船公司中文名")
    private String carrierCN;

    @ApiModelProperty("船公司英文名")
    private String carrierEN;


}
