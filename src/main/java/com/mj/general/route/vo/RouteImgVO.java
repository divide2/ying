package com.mj.general.route.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/11 15:39
 */
@Data
@Builder
public class RouteImgVO {

    @ApiModelProperty("航线图")
    private String routeMapUrl;
}
