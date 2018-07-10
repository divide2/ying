package com.mj.general.port.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @auther: zejun
 * @date: 2018/7/9 18:34
 */
@Data
@ApiModel("世界港口查询")
public class PortQueryDTO {

    @ApiModelProperty("港口代码/港口中英文名/国家中英文/航线名称")
    private String keyName;
}
