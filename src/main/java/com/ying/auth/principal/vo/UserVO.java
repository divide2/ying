package com.ying.auth.principal.vo;

import com.ying.auth.principal.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author bvvy
 * TODO
 */
@Builder
@Data
@ApiModel("用户基础信息")
public class UserVO {
    @ApiModelProperty("id")
    private Integer id;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String phone;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 启用
     */
    @ApiModelProperty("启用")
    private Boolean enabled;

    public static UserVO fromUser(User user) {
        return UserVO.builder()
                .id(user.getId())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .enabled(user.isEnabled())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .phone(user.getPhone())
                .username(user.getUsername())
                .build();

    }
}
