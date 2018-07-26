package com.mj.general.container.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author zejun
 * @date 2018/7/26 15:51
 */
@Data
@ApiModel("柜型新增")
public class ContainerAddDTO {
    /**
     * 柜型简称
     */
    @ApiModelProperty("柜型简称")
    @NotEmpty
    private String containerCode;

    /**
     *柜型中文名
     */
    @ApiModelProperty("柜型中文名")
    @NotEmpty
    private String containerCN;

    /**
     *柜型英文名
     */
    @ApiModelProperty("柜型英文名")
    @NotEmpty
    private String containerEN;
}
