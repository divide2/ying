package com.ying.auth.principal.controller;

import com.ying.auth.principal.model.User;
import com.ying.auth.principal.service.UserService;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Jsoner;
import com.ying.core.er.Responser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author bvvy
 * @date 2018/8/28
 */
@RestController
@Api(tags = "微信小程序接口")
class MpController {

    private static final String URL = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String APPID = "wx3fdfbf00e5a3b9f9";
    private static final String SECRET = "4d17a936b70d0786f59d1fa972c92c49";

    private final UserService userService;

    MpController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login/mp")
    @ApiOperation("小程序登录")
    public ResponseEntity<Messager> login(@RequestBody @Valid MpLogin login, BindingResult br) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL+"?appid="+APPID+"&secret="+SECRET+"&js_code="+login.getCode()+"&grant_type=authorization_code", String.class);
        MpLoginReturn open = Jsoner.fromSnake(result, MpLoginReturn.class);
        String openid = open.getOpenid();
        User user = userService.getByUsername(openid);
        if (user == null) {
            user = new User();
        }
        user.setUsername(openid);
        user.setNickname(login.getNickName());
        user.setAvatar(login.getAvatarUrl());
        user.setPassword(openid);
        user.setWechatOpenId(openid);
        user.setGender(login.getGender());
        user.setEnabled(true);
        userService.update(user);
        return Responser.created();
    }

}

@Data
class MpLogin {
    @NotEmpty
    private String code;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private String city;
    private String province;
    private String country;
    private String language;
}

@Data
class MpLoginReturn {
    private String openid;
    private String sessionKey;
    private String errcode;
    private String errmsg;
    private List<String> hints;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Skey {
    private String skey;
}