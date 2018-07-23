package com.mj.general.dictionary.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zejun
 * @date 2018/7/20 11:31
 */
@Data
@ApiModel("启用/禁用状态")
public class GeneralEnabledDTO {

    @ApiModelProperty("id")
    @NotNull
    private Integer id;

    @ApiModelProperty("状态")
    @NotEmpty
    private Character enabled;
}
