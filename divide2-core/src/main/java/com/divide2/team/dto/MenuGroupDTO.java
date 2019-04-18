package com.divide2.team.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * @date 2019/2/27
 */
@Data
public class MenuGroupDTO {

    @NotEmpty
    private String teamId;
    @NotEmpty
    private String name;
}
