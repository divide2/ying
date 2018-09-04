package com.ying.auth.principal.vo;

import lombok.Data;

/**
 * @author bvvy
 * @date 2018/9/4
 */
@Data
public class MpUserVO {
    private String nickName;
    private String avatarUrl;
    private String gender;
    private String city;
    private String province;
    private String country;
    private String language;
}
