package com.mj.general.port.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author zejun
 * @date 2018/7/9 18:12
 */
@Data
@ApiModel("新增世界港口")
public class PortAddDTO {

    @ApiModelProperty("港口代码")
    @NotEmpty
    private String portCode;

    @ApiModelProperty("港口中文名")
    @NotEmpty
    private String portCN;

    @ApiModelProperty("港口英文名")
    @NotEmpty
    private String portEN;

    @ApiModelProperty("国家（地区）中文名")
    @NotEmpty
    private String countryCN;

    @ApiModelProperty("国家（地区）英文名")
    @NotEmpty
    private String countryEN;

    @ApiModelProperty("国家（地区）代码")
    @NotEmpty
    private String countryCode;

    @ApiModelProperty("所属航线")
    @NotEmpty
    private String serviceName;

    @ApiModelProperty("禁用状态")
    @NotEmpty
    private String status;
}
