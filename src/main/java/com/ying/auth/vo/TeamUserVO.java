package com.ying.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bvvy
 * @date 2019/2/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamUserVO {

    private RoleVO role;
    private UserVO user;
}
