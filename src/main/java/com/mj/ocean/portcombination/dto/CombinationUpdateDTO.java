package com.mj.ocean.portcombination.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zejun
 * @date 2018/7/13 17:40
 */
@Data
@ApiModel("修改港口组")
public class CombinationUpdateDTO {

    @ApiModelProperty("港口组id")
    @NotNull
    private Integer id;

    @ApiModelProperty("船公司ids")
    @NotNull
    private String carrierIds;

    @ApiModelProperty("组合名称")
    @NotEmpty
    private String combinationName;

    @ApiModelProperty("港口ids")
    @NotEmpty
    private String portIds;
}
