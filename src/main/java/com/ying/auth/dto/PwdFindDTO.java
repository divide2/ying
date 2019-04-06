package com.ying.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * @date 2019/4/8
 */
@Data
public class PwdFindDTO {
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String verifyCode;

    @NotEmpty
    private String password;

    @NotEmpty
    private String rePassword;
}
