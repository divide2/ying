package com.ying.auth.principal.controller;

import com.ying.auth.principal.model.User;
import com.ying.auth.principal.service.UserService;
import com.ying.auth.principal.vo.MpUserVO;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Jsoner;
import com.ying.core.er.Responser;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @author bvvy
 * @date 2018/8/28
 */
@RestController
class MpController {

    private static final String URL = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String APPID = "wx3fdfbf00e5a3b9f9";
    private static final String SECRET = "4d17a936b70d0786f59d1fa972c92c49";

    private final OAuth2ClientContext context;
    private final UserService userService;

    MpController(OAuth2ClientContext context, UserService userService) {
        this.context = context;
        this.userService = userService;
    }


    @PostMapping("/login/mp")
    public ResponseEntity<DefaultOAuth2AccessToken> login(@RequestBody @Valid MpCode code, BindingResult br) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL+"?appid="+APPID+"&secret="+SECRET+"&js_code="+code.getCode()+"&grant_type=authorization_code", String.class);
        MpLoginReturn open = Jsoner.fromSnake(result, MpLoginReturn.class);
        return Responser.ok(new DefaultOAuth2AccessToken(open.getSessionKey()));
    }

    @PostMapping("/v1/mp/user")
    public ResponseEntity<Messager> saveMpUser(MpUserVO mpUser) {
//        User user = userService.getByUsername("123");
        User user = new User();
        user.setUsername("123");
        user.setNickname(mpUser.getNickName());
        user.setAvatar(mpUser.getAvatarUrl());
        user.setPassword("132");
        user.setGender(mpUser.getGender());
        return Responser.created();
    }
}

@Data
class MpCode {
    @NotEmpty
    private String code;
}

@Data
class MpLoginReturn {
    private String openid;
    private String sessionKey;
}