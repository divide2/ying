package com.divide2.team.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 */
@Data
@ApiModel("角色修改")
public class RoleUpdateDTO {
    @ApiModelProperty("id")
    @NotNull
    private Integer id;

    /**
     * 角色编码
     */
    @ApiModelProperty("角色编码")
    @NotEmpty
    private String code;
    /**
     * 角色名称
     */
    @NotEmpty
    @ApiModelProperty("角色名称")
    private String name;
}
