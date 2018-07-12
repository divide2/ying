package com.mj.general.route.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/11 11:31
 */
@Data
@ApiModel("航线查询")
public class RouteQueryDTO {

    @ApiModelProperty("船公司英文名")
    private String carrierEN;

    @ApiModelProperty("航线代码")
    private String routeCode;

    @ApiModelProperty("港口英文名")
    private String allPort;
}
