package com.mj.auth.principal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色添加权限
 *
 * @author bvvy
 */
@Data
@ApiModel("角色添加权限")
public class RoleAddAuthDTO {

    @NotNull
    @ApiModelProperty("角色id")
    private Integer roleId;
    
    @NotEmpty
    @ApiModelProperty("资源ids")
    private List<Integer> resIds;
}
