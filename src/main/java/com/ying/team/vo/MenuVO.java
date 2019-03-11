package com.ying.team.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 */
@Data
@ApiModel("菜单")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuVO {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("父级菜单")
    private String pid;

    @ApiModelProperty("地址")
    private String path;

    @ApiModelProperty("是否启用")
    private Boolean enabled;

    @ApiModelProperty("排序号")
    private Integer orderNum;

    private String icon;


    @ApiModelProperty("授权码")
    private String authority;

    @ApiModelProperty("快捷方式")
    private Boolean shortcut;

    private String color;


}
