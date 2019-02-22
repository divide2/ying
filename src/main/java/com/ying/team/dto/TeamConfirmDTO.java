package com.ying.team.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * @date 2019/2/17
 */
@Data
public class TeamConfirmDTO {

    @NotEmpty
    private String teamApplicationId;
    private String memoName;
    private Integer roleId;

}
