package com.ying.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 */
@Data
@ApiModel("注册")
public class JoinDTO {
    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String verifyCode;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nickname;

}