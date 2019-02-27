package com.ying.team.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * @date 2019/2/27
 */
@Data
public class GroupMenuDTO {
    @NotEmpty
    private String teamId;
    @NotEmpty
    private String menuGroupId;
    @NotEmpty
    private String menuId;
}
