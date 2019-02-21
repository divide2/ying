package com.ying.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * @date 2019/2/17
 */
@Data
public class GroupConfirmDTO {

    @NotEmpty
    private String groupApplicationId;
    private String memoName;
    private Integer roleId;

}
