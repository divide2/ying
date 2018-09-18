package com.ying.auth.res.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer pid;

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

    @ApiModelProperty("类型")
    @NotEmpty
    private String type;


    @ApiModelProperty("编码")
    @NotEmpty
    private String code;

    @ApiModelProperty("图标")
    private String icon;
}
