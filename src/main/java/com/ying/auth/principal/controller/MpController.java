package com.ying.auth.principal.controller;

import com.ying.core.er.Jsoner;
import lombok.Data;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    MpController(OAuth2ClientContext context) {
        this.context = context;
    }


    @GetMapping("/login/mp")
    public DefaultOAuth2AccessToken login(MpCode code) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL+"?appid="+APPID+"&secret="+SECRET+"&js_code="+code.getCode()+"&grant_type=authorization_code", String.class);
        MpLoginReturn open = Jsoner.fromSnake(result, MpLoginReturn.class);
        return new DefaultOAuth2AccessToken(open.getSessionKey());
    }
}

@Data
class MpCode {
    private String code;
}

@Data
class MpLoginReturn {
    private String openid;
    private String sessionKey;
}