package com.mj.auth.res.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 * 菜单添加TO
 */
@Data
@ApiModel(value = "Menu", description = "Menu Add")
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
    private String path;

    /**
     * 是否启用
     */
    @NotNull
    @ApiModelProperty("启用")
    private Boolean enabled;

    /**
     * 排序号
     */
    @ApiModelProperty("排序")
    @Column(name = "order_num")
    private Integer orderNum;
}
