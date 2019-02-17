package com.ying.auth.vo;

import lombok.Data;

import java.util.List;

/**
 * @author bvvy
 * @date 2019/2/17
 */
@Data
public class GroupUserVO {

    private GroupVO group;
    private List<UserVO> user;
}
