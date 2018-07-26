package com.mj.general.container.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author zejun
 * @date 2018/7/26 16:07
 */
@Data
@ApiModel("验证字段")
public class ContainerCheckDTO {

    @ApiModelProperty("柜型简称")
    @NotEmpty
    private String containerCode;

}
