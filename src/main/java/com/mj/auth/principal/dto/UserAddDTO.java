package com.mj.auth.principal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * todo
 */
@Data
public class UserAddDTO {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名,用于登录")
    @NotEmpty
    @Length(min = 4, max = 20)
    private String username;


    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotEmpty
    @Length(min = 6, max = 40)
    private String password;

    @ApiModelProperty("重复密码")
    @NotEmpty
    @Length(min = 6, max = 40)
    private String rePassword;


    @NotEmpty
    @ApiModelProperty("昵称，用于显示")
    @Length(min = 2, max = 9)
    private String nickname;
}
