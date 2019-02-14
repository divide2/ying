package com.ying.client.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bvvy
 * @date 2019/2/14
 */
@Data
public class UserInfoVO {

    @ApiModelProperty("朋友的id")
    private Integer userId;

    @ApiModelProperty("备注名")
    private String memoName;

    private String avatar;

    private String username;
    private String nickname;
    private String phone;
    private String email;
    private String gender;
    private boolean friend;
}
