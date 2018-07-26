package com.mj.general.container.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author zejun
 * @date 2018/7/26 15:47
 */
@Data
@Builder
public class ContainerVO {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("柜型简称")
    private String containerCode;

    @ApiModelProperty("柜型中文名")
    private String containerCN;

    @ApiModelProperty("柜型英文名")
    private String containerEN;

    @ApiModelProperty("状态值")
    private Character enabled;
}
