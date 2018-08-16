package com.ying.auth.principal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author bvvy
 */
@Data
@ApiModel("添加角色用户")
public class RoleAddUsersDTO {

    @NotNull
    @ApiModelProperty("角色id")
    private Integer roleId;

    @NotEmpty
    @ApiModelProperty("用户ids")
    private List<Integer> userIds;
}
