package com.divide2.team.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 * 菜单添加TO
 */
@Data
@ApiModel(value = "Menu", description = "Menu Add")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuAddDTO {
    /**
     * 名称
     */
    @NotEmpty
    @ApiModelProperty("菜单名称")
    private String name;

    /**
     * 父级菜单
     */

    @NotNull
    @ApiModelProperty("父级id")
    private String pid;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    @NotEmpty
    private String path;

    /**
     * 是否启用
     */
    @ApiModelProperty("启用")
    private Boolean enabled;

    /**
     * 排序号
     */
    @ApiModelProperty("排序")
    @NotNull
    private Integer orderNum;

    @ApiModelProperty("图标")
    private String icon;
    /**
     * 授权码
     */
    @ApiModelProperty("授权码")
    private String authority;


    @Type(type = "yes_no")
    @ApiModelProperty("是否有快捷方式")
    private Boolean shortcut;
}
