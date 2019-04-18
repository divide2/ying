package com.divide2.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * @date 2018/9/4
 */
@Data
@ApiModel("微信小程序用户信息")
public class MpUserVO {
    private String nickName;
    private String avatarUrl;
    private String gender;
    private String city;
    private String province;
    private String country;
    private String language;
    @NotEmpty
    @ApiModelProperty("返回的skey")
    private String skey;

}
