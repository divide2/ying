package com.mj.general.container.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zejun
 * @date 2018/7/26 15:56
 */
@Data
@ApiModel("柜型修改")
public class ContainerUpdateDTO {

    @ApiModelProperty("id")
    @NotNull
    private Integer id;

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
    private String containerCN;

    /**
     *柜型英文名
     */
    @ApiModelProperty("柜型英文名")
    private String containerEN;
}
