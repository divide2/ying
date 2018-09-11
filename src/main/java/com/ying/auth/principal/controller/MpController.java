package com.ying.auth.principal.controller;

import com.ying.auth.principal.model.User;
import com.ying.auth.principal.service.UserService;
import com.ying.auth.principal.vo.MpUserVO;
import com.ying.core.data.resp.Messager;
import com.ying.core.er.Jsoner;
import com.ying.core.er.Responser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    protected AccessTokenRequest accessTokenRequest(@Value("#{request.parameterMap}")
                                                            Map<String, String[]> parameters, @Value("#{request.getAttribute('currentUri')}")
                                                            String currentUri) {
        DefaultAccessTokenRequest request = new DefaultAccessTokenRequest(parameters);
        request.setCurrentUri(currentUri);
        return request;
    }

    @Bean
    @Scope(
            value = "session",
            proxyMode = ScopedProxyMode.INTERFACES
    )
    public OAuth2ClientContext oauth2ClientContext(AccessTokenRequest accessTokenRequest) {
        return new DefaultOAuth2ClientContext(accessTokenRequest);
    }
    private final OAuth2ClientContext clientContext;

    private static Map<String, String> SKEY_KEEPER = new ConcurrentHashMap<>();
    MpController(UserService userService, OAuth2ClientContext clientContext) {
        this.userService = userService;
        this.clientContext = clientContext;
    }


    @PostMapping("/login/mp")
    @ApiOperation("小程序登录")
    public ResponseEntity<Skey> login(@RequestBody @Valid MpCode code, BindingResult br) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL+"?appid="+APPID+"&secret="+SECRET+"&js_code="+code.getCode()+"&grant_type=authorization_code", String.class);
        MpLoginReturn open = Jsoner.fromSnake(result, MpLoginReturn.class);
        SKEY_KEEPER.put(open.getSessionKey(), open.getOpenid());
        return Responser.ok(new Skey(open.getSessionKey()));
    }

    @PostMapping("/v1/mp/user")
    @ApiOperation("保存用户")
    public ResponseEntity<Messager> saveMpUser(@RequestBody @Valid MpUserVO mpUser,BindingResult br) {
//        User user = userService.getByUsername("123");
        String openId = SKEY_KEEPER.get(mpUser.getSkey());
        User user = userService.getByUsername(openId);
        if (user == null) {
            user = new User();
        }
        user.setUsername(openId);
        user.setNickname(mpUser.getNickName());
        user.setAvatar(mpUser.getAvatarUrl());
        user.setPassword(openId);
        user.setGender(mpUser.getGender());
        user.setEnabled(true);
        userService.update(user);
        this.login(user.getUsername(), user.getPassword());
        return Responser.created();

    }


    private void login(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", Base64.encodeBase64String("aiNzsAXE8tkOFJN6:12345678".getBytes()));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>(4);
        body.add("socpe", "webclient");
        body.add("username", username);
        body.add("password", password);
        body.add("grant_type", "password");
        HttpEntity<MultiValueMap<String, String>> form = new HttpEntity<>(body, headers);
        ResponseEntity<String> result = restTemplate.postForEntity("http://127.0.0.1:8081/oauth/token", form, String.class);
        System.out.println(result);
    }


    public static void main(String[] args) {
        System.out.println(Base64.encodeBase64String("aiNzsAXE8tkOFJN6:12345678".getBytes()));
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

@Data
@NoArgsConstructor
@AllArgsConstructor
class Skey {
    private String skey;
}