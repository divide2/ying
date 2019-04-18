package com.divide2.friend.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/12/11
 */
@Data
public class FriendVO {

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


}
