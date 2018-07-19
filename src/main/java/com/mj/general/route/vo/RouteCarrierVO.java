package com.mj.general.route.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/18 16:42
 */
@Data
@Builder
public class RouteCarrierVO {
    @ApiModelProperty("航线id")
    private Integer id;

    @ApiModelProperty("船公司id")
    private Integer carrierId;

    @ApiModelProperty("航线代码")
    private String routeCode;
}
