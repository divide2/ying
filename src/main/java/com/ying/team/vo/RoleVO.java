package com.ying.team.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author bvvy
 */
@Data
@Builder
public class RoleVO {

    @ApiModelProperty("id")
    private Integer id;

    /**
     * 角色编码
     */
    @ApiModelProperty("角色编码")
    private String code;
    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String name;
}
