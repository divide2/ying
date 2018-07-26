package com.mj.general.container.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author zejun
 * @date 2018/7/26 16:15
 */
@Data
@ApiModel("柜型查询")
public class ContainerQueryDTO {
    @ApiModelProperty("柜型简称")
    private String containerCode;
}
