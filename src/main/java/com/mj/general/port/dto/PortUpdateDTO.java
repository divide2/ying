package com.mj.general.port.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zejun
 * @date 2018/7/9 18:23
 */
@Data
@ApiModel("修改世界港口")
public class PortUpdateDTO {

    @ApiModelProperty("世界港口id")
    @NotNull
    private Integer id;

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
    @NotNull
    private Character enabled;
}
