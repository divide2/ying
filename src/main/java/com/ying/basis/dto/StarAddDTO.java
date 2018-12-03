package com.ying.basis.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/9/11
 */
@Data
@ApiModel("点赞添加")
public class StarAddDTO {

    @ApiModelProperty("被点赞的对象id")
    private Integer toId;

    @ApiModelProperty("被点赞的对象名称")
    private String toName;

    @ApiModelProperty("被点赞的对象类型")
    private String type;
}
