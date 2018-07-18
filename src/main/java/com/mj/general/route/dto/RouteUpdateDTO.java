package com.mj.general.route.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zejun
 * @date 2018/7/10 15:48
 */
@Data
@ApiModel("修改航线")
public class RouteUpdateDTO {

    @ApiModelProperty("航线id")
    @NotNull
    private Integer id;

    @ApiModelProperty("船公司id")
    @NotNull
    private Integer carrierId;

    @ApiModelProperty("航线代码")
    @NotEmpty
    private String routeCode;

    @ApiModelProperty("航线全称")
    @NotEmpty
    private String routeFullName;

    @ApiModelProperty("航线描述")
    @NotEmpty
    private String routeDesc;

    @ApiModelProperty("航线图")
    @NotEmpty
    private String routeMapUrl;

    @ApiModelProperty("港口数据集")
    @NotEmpty
    private List<RoutePortAddDTO> routePorts;
}
