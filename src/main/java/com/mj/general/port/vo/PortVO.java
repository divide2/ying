package com.mj.general.port.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @auther: zejun
 * @date: 2018/7/9 18:03
 */
@Data
@Builder
public class PortVO {

    @ApiModelProperty("世界港口id")
    private Integer id;

    @ApiModelProperty("港口代码")
    private String portCode;

    @ApiModelProperty("港口中文名")
    private String portCN;

    @ApiModelProperty("港口英文名")
    private String portEN;

    @ApiModelProperty("国家（地区）中文名")
    private String countryCN;

    @ApiModelProperty("国家（地区）英文名")
    private String countryEN;

    @ApiModelProperty("国家（地区）代码")
    private String countryCode;

    @ApiModelProperty("所属航线")
    private String serviceName;

    @ApiModelProperty("禁用状态")
    private String status;
}
