package com.mj.general.route.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/12 09:28
 */
@Data
@ApiModel("检查字段")
public class RouteCheckDTO {

    @ApiModelProperty("航线代码")
    private String routeCode;
}
