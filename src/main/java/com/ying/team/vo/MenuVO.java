package com.ying.team.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 */
@Data
@ApiModel("菜单")
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO {

    @ApiModelProperty("id")
    private String id;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 父级菜单
     */
    @ApiModelProperty("父级菜单")
    private Integer pid;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String path;

    /**
     * 是否启用
     */
    @ApiModelProperty("是否启用")
    private Boolean enabled;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private Integer orderNum;

    private String icon;

    private String code;


}
