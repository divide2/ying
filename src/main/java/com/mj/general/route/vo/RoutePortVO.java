package com.mj.general.route.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * @author zejun
 * @date 2018/7/10 17:14
 */
@Data
@Builder
public class RoutePortVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("港口id")
    private Integer portId;

    @ApiModelProperty("港口英文名")
    private  String portEN;

    @ApiModelProperty("国家（地区）英文名")
    private String countryEN;

    @ApiModelProperty("截关时间")
    private LocalDateTime etc;

    @ApiModelProperty("开船时间")
    private LocalDateTime etd;

    @ApiModelProperty("航程")
    private BigDecimal tt;

    @ApiModelProperty("码头")
    private String pier;

    @ApiModelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty("航线id")
    private Integer routeId;
}
