package com.mj.general.port.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @auther: zejun
 * @date: 2018/7/12 09:15
 */
@Data
@ApiModel("检查字段")
public class PortCheckDTO {

    @ApiModelProperty("港口代码")
    @NotEmpty
    private String portCode;
}
