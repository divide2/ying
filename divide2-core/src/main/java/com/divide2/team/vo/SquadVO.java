package com.divide2.team.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SquadVO {

    @ApiModelProperty("id")
    private String id;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String name;
}
