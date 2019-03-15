package com.ying.team.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;

/**
 * 权限
 * @author bvvy
 * @date 2019/2/26
 */
@Data
public class AclDTO {

    private Collection<String> menuIds;

    @NotEmpty
    private String teamId;

    @NotEmpty
    @ApiModelProperty("用户或者是小组的id")
    private String principleId;

    @NotEmpty
    @ApiModelProperty("user或者squad")
    private String principleType;
}
