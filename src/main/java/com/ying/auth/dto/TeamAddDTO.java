package com.ying.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bvvy
 * @date 2019/2/13
 */
@Data
public class TeamAddDTO {

    @ApiModelProperty("名字")
    private String name;

}
