package com.mj.general.carrier.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/9 15:14
 */
@Data
@Builder
public class CarrierVO {

    @ApiModelProperty("船公司id")
    private Integer id;

    @ApiModelProperty("船公司简称")
    private String carrierCode;

    @ApiModelProperty("船公司中文名")
    private String carrierCN;

    @ApiModelProperty("船公司英文名")
    private String carrierEN;

    @ApiModelProperty("状态")
    private char status;
}
