package com.mj.general.route.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zejun
 * @date 2018/7/10 17:07
 */
@Data
@Builder
public class RouteVO {

    @ApiModelProperty("航线id")
    private Integer id;

    @ApiModelProperty("船公司id")
    private Integer carrierId;

    @ApiModelProperty("船公司简称")
    private String carrierCode;

    @ApiModelProperty("船公司英文名")
    private String carrierEN;

    @ApiModelProperty("航线代码")
    private String routeCode;

    @ApiModelProperty("航线全称")
    private String routeFullName;

    @ApiModelProperty("航线描述")
    private String routeDesc;

    @ApiModelProperty("航线图")
    private String routeMapUrl;

    @ApiModelProperty("禁用状态")
    private Character enabled;

    @ApiModelProperty("总的港口数")
    private Integer num;

    @ApiModelProperty("首靠港")
    private String firstPort;

    @ApiModelProperty("最后靠港")
    private String lastPort;

    @ApiModelProperty("全程时间")
    private BigDecimal allTime;

    @ApiModelProperty("港口数据集")
    private List<RoutePortVO> routePorts;
}
