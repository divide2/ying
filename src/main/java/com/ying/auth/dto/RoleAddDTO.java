package com.ying.auth.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * 角色添加dto
 */
@Data
@ApiModel("角色添加")
public class RoleAddDTO {
    /**
     * 角色编码
     */
    @ApiModelProperty("角色编码")
    @NotEmpty
    private String code;
    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    @NotEmpty
    private String name;
}
