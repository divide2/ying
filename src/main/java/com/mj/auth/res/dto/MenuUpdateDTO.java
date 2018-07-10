package com.mj.auth.res.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author bvvy
 */
@ApiModel(value = "Menu",description = "修改菜单")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuUpdateDTO {

    @ApiModelProperty("id")
    @NotNull
    private Integer id;
    /**
     * 名称
     */
    @NotEmpty
    @ApiModelProperty("菜单名称")
    @Length(max = 20, min = 2)
    private String name;

    /**
     * 父级菜单
     */
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
