package com.ying.auth.principal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/7/27
 */
@ApiModel("用户搜索")
@Data
public class UserQueryDTO {

    @ApiModelProperty("关键词")
    private String keyword;
}
